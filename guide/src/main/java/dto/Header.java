package dto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuanyu peng
 * @description:
 * @date 2023/8/21 22:02
 */
public class Header {
    private int crcCode = 0xabef0101;

    private int length;

    private long sessionID;

    // 类型
    private byte type;

    // 优先级
    private byte priority;

    private Map<String, Object> attachment = new ConcurrentHashMap<>();

    public Header() {
    }

    public Header(final int crcCode, final int length, final long sessionID, final byte type, final byte priority, final Map<String, Object> attachment) {
        this.crcCode = crcCode;
        this.length = length;
        this.sessionID = sessionID;
        this.type = type;
        this.priority = priority;
        this.attachment = attachment;
    }

    public int getCrcCode() {
        return this.crcCode;
    }

    public void setCrcCode(final int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(final int length) {
        this.length = length;
    }

    public long getSessionID() {
        return this.sessionID;
    }

    public void setSessionID(final long sessionID) {
        this.sessionID = sessionID;
    }

    public byte getType() {
        return this.type;
    }

    public void setType(final byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return this.priority;
    }

    public void setPriority(final byte priority) {
        this.priority = priority;
    }

    public Map<String, Object> getAttachment() {
        return this.attachment;
    }

    public void setAttachment(final Map<String, Object> attachment) {
        this.attachment = attachment;
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
        return "Header [crcCode=" + this.crcCode + ", length=" + this.length + ", sessionID=" + this.sessionID + ", type=" + this.type + ", priority=" + this.priority + ", attachment=" + this.attachment + "]";
    }
}
