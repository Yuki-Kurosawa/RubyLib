package yuki.control.extended;

/**
 * On Get File Handler
 */
public interface OnGetFile extends OnGet {
    /**
     * Response Method
     *
     * @param fileName FilePath saved
     */
    public void Get(String fileName);
}
