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
}
