emailrow(e) ::= <<
<tr><td>$e.from$</td><td><a href="/viewMail?emailId=$e.id$">
                            $if(e.read)$
                            $e.subject$
                            $else$
                            $e:unread()$
                            $endif$</a></td><td>$e.date$</td><td><a href="/trashEmail?emailId1=$e.id$">
                            <input type="submit" value="Delete" class="btn btn-danger btn-sm"></a></td></tr>
>>