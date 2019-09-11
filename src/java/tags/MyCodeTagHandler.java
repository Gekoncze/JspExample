package tags;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;


public class MyCodeTagHandler extends BodyTagSupport {
    @Override
    public int doAfterBody() {
        BodyContent body = getBodyContent();
        String bodyAsString = body.getString();
        bodyAsString = bodyAsString.replace("\r\n", "\n");
        bodyAsString = bodyAsString.replace("\n\r", "\n");
        String[] lines = bodyAsString.split("\n");
        try {
            JspWriter out = body.getEnclosingWriter();
            out.print("<textarea wrap=\"off\" cols=\"75\" rows=\"25\" >");
            int numberOfDigits = (lines.length + "").length();
            for(int i = 0; i < lines.length; i++){
                out.print(fillDummyBefore("" + (i+1), numberOfDigits) + "&nbsp&nbsp" + lines[i].trim());
                if(i != (lines.length - 1)) out.print("&#13;&#10;");
            }
            out.print("</textarea>");
        } catch (IOException e) {
            System.err.println("Could not write body content: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        return SKIP_BODY;
    }
    
    private static String fillDummyBefore(String string, int length){
        while(string.length() < length){
            string = " " + string;
        }
        string = string.replace(" ", "&nbsp");
        return string;
    }
}
