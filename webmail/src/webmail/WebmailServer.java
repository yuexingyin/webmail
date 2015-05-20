package webmail;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import webmail.misc.STListener;
import webmail.pages.*;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Class;import java.lang.Exception;import java.lang.String;import java.lang.System;import java.util.HashMap;
import java.util.Map;

public class WebmailServer {
	public static final String WEBMAIL_TEMPLATES_ROOT = "webmail/resources/templates";

	public static final STListener stListener = new STListener();

	public static Map<String,Class> mapping = new HashMap<String, Class>();
	static {
		mapping.put("/", HomePage.class);
		mapping.put("/users", UserListPage.class);
        mapping.put("/registpage", RegistPage.class);
        mapping.put("/regist",Regist.class);
        mapping.put("/login", Login.class);
        mapping.put("/logout",LogOut.class);
        mapping.put("/mainpage",MainPage.class);
        mapping.put("/email",EmailPage.class);
        mapping.put("/sentMail",SentMailPage.class);
        mapping.put("/spam",SpamPage.class);
        mapping.put("/trash",TrashPage.class);
        mapping.put("/sendMail",Send.class);
        mapping.put("/viewMail",ViewEmailPage.class);
        mapping.put("/checkInbox",CheckInbox.class);
        mapping.put("/forwardPage",ForwardPage.class);
        mapping.put("/forwardEmail",ForwardEmail.class);
        mapping.put("/replyPage",ReplyPage.class);
        mapping.put("/replyEmail",ReplyEmail.class);
        mapping.put("/trashEmail",TrashTheMail.class);
        mapping.put("/emptyEmail",EmptyTrash.class);
        mapping.put("/settings",ProfilePage.class);
        mapping.put("/edit",Edit.class);
        mapping.put("/contactPage",ContactPage.class);
        mapping.put("/addContact",AddContact.class);
        mapping.put("/deleteContact",DeleteContact.class);
        mapping.put("/sendContact",SendContact.class);
        mapping.put("/sortPage",SortPage.class);
        mapping.put("/searchPage",SearchPage.class);
        mapping.put("/searchResult",SearchResult.class);

	}

	public static void main(String[] args) throws Exception {
		if ( args.length<2 ) {
			System.err.println("java webmail.Server static-files-dir log-dir");
			System.exit(1);
		}
		String staticFilesDir = "/opt/webmail";
		String logDir = "/var/log/webmail";


		ServletContextHandler context = new
		ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");


        String jettyDistKeystore = "webmail/resources/keystore";
        String keystorePath = jettyDistKeystore;
        File keystoreFile = new File(keystorePath);
        if (!keystoreFile.exists()){
            throw new FileNotFoundException(keystoreFile.getAbsolutePath());
        }

        Server server = new Server();

        // HTTP Configuration

        HttpConfiguration http_config = new HttpConfiguration();
        http_config.setSecureScheme("https");
        http_config.setSecurePort(8443);
        http_config.setOutputBufferSize(32768);

        // HTTP connector

        ServerConnector http = new ServerConnector(server,
        new HttpConnectionFactory(http_config));
        http.setPort(8080);
        http.setIdleTimeout(30000);

        // SSL Context Factory for HTTPS and SPDY

        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath(keystoreFile.getAbsolutePath());
        sslContextFactory.setKeyStorePassword("OBF:1vny1zlo1x8e1vnw1vn61x8g1zlu1vn4");
        sslContextFactory.setKeyManagerPassword("OBF:1u2u1wml1z7s1z7a1wnl1u2g");

        // HTTPS Configuration
        // Server.
        HttpConfiguration https_config = new HttpConfiguration(http_config);
        https_config.addCustomizer(new SecureRequestCustomizer());

        // HTTPS connector

        ServerConnector https = new ServerConnector(server,
                                 new SslConnectionFactory(sslContextFactory, "http/1.1"),
                                 new HttpConnectionFactory(https_config));
        https.setPort(8443);
        https.setIdleTimeout(500000);

        server.setConnectors(new Connector[] { http, https });

		// add a simple Servlet at "/dynamic/*"
        ServletHolder holderDynamic = new ServletHolder("dynamic", DispatchServlet.class);
		context.addServlet(holderDynamic, "/*");

        // add special pathspec of "/home/" content mapped to the homePath
        ServletHolder holderHome = new ServletHolder("static-home", DefaultServlet.class);
        holderHome.setInitParameter("resourceBase",staticFilesDir);
        holderHome.setInitParameter("dirAllowed","true");
        holderHome.setInitParameter("pathInfoOnly","true");
		context.addServlet(holderHome, "/files/*");

        // Lastly, the default servlet for root content (always needed, to satisfy servlet spec)
        // It is important that this is last.
        ServletHolder holderPwd = new ServletHolder("default", DefaultServlet.class);
        holderPwd.setInitParameter("resourceBase","/tmp/foo");
        holderPwd.setInitParameter("dirAllowed","true");
		context.addServlet(holderPwd, "/");

		// log using NCSA (common log format)
		// http://en.wikipedia.org/wiki/Common_Log_Format
		NCSARequestLog requestLog = new NCSARequestLog();
		requestLog.setFilename(logDir + "/yyyy_mm_dd.request.log");
		requestLog.setFilenameDateFormat("yyyy_MM_dd");
		requestLog.setRetainDays(90);
		requestLog.setAppend(true);
		requestLog.setExtended(true);
		requestLog.setLogCookies(false);
		requestLog.setLogTimeZone("GMT");
		RequestLogHandler requestLogHandler = new RequestLogHandler();
		requestLogHandler.setRequestLog(requestLog);
		requestLogHandler.setServer(server);

        HandlerCollection handlerCollection=new HandlerCollection();
        handlerCollection.addHandler(requestLogHandler);
        handlerCollection.addHandler(context);
        server.setHandler(handlerCollection);

		server.start();
		server.join();
	}
}
