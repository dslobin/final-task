package by.epam.autoshow.tag;

import by.epam.autoshow.model.SaleStatus;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class CarSaleStatusTag extends TagSupport {
    private static final String COLOR_GREEN = "#008000";
    private static final String COLOR_BLACK = "#000000";
    private static final String COLOR_VIOLET = "#EE82EE";
    private SaleStatus status;

    public CarSaleStatusTag() {
    }

    public void setCarSaleStatus(SaleStatus status) {
        this.status = status;
    }

    @Override
    public int doStartTag() throws JspException {
        if (status != null) {
            try {
                String color;
                switch (status) {
                    case IN_STOCK:
                        color = COLOR_GREEN;
                        break;
                    case SOLD:
                        color = COLOR_VIOLET;
                        break;
                    default:
                        color = COLOR_BLACK;
                        break;
                }
                pageContext.getOut()
                        .write(" <b style=\"color: " + color + "\">" + status + "</b>");
            } catch (IOException e) {
                throw new JspException(e);
            }
        }
        return SKIP_BODY;
    }
}
