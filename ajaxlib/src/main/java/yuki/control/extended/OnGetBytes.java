package yuki.control.extended;

/*On Get Bytes Response Handler*/
public interface OnGetBytes extends OnGet {
    /**
     * Response Method
     *
     * @param data Response DataBytes
     */
    public void Get(byte[] data);
}
