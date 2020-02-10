package by.epam.autoshow.model.car;

import java.util.Objects;

public class BodyType {
    private Long bodyTypeId;
    private String title;

    public BodyType(Long id, String title) {
        this.bodyTypeId = id;
        this.title = title;
    }

    public BodyType() {
        super();
    }

    public Long getBodyTypeId() {
        return bodyTypeId;
    }

    public void setBodyTypeId(Long bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BodyType bodyType = (BodyType) o;
        return Objects.equals(title, bodyType.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BodyType{");
        sb.append("bodyTypeId=").append(bodyTypeId);
        sb.append(", title='").append(title).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
