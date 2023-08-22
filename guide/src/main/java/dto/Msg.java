package dto;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/8/21 22:04
 */
public class Msg {
    private Header header;

    private Object body;

    public Msg(final Header header, final Object body) {
        this.header = header;
        this.body = body;
    }

    public Msg() {
    }

    public Header getHeader() {
        return this.header;
    }

    public void setHeader(final Header header) {
        this.header = header;
    }

    public Object getBody() {
        return this.body;
    }

    public void setBody(final Object body) {
        this.body = body;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "Msg [header=" + this.header + ", body=" + this.body + "]";
    }
}
