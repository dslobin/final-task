package by.epam.autoshow.tag;

import by.epam.autoshow.model.user.UserRole;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import java.io.IOException;

@SuppressWarnings("serial")
public class UserRoleTag extends TagSupport {
    private static final String COLOR_FOREST_GREEN = "#228B22";
    private static final String COLOR_LIGHT_SEA_GREEN = "#20B2AA";
    private static final String COLOR_BLACK = "#000000";
    private static final String COLOR_ORANGE_RED = "#FF4500";
    private UserRole role;

    public UserRoleTag() {
    }

    public void setUserRole(UserRole role) {
        this.role = role;
    }

    @Override
    public int doStartTag() throws JspException {
        if (role != null) {
            try {
                String color;
                switch (role) {
                    case CLIENT:
                        color = COLOR_FOREST_GREEN;
                        break;
                    case GUEST:
                        color = COLOR_ORANGE_RED;
                        break;
                    case ADMIN:
                        color = COLOR_LIGHT_SEA_GREEN;
                        break;
                    default:
                        color = COLOR_BLACK;
                        break;
                }
                pageContext.getOut()
                        .write(" <b style=\"color: " + color + "\">" + role + "</b>");
            } catch (IOException e) {
                throw new JspException(e);
            }
        }
        return SKIP_BODY;
    }
}