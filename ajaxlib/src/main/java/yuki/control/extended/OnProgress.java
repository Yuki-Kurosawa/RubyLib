package yuki.control.extended;

/**
 * Progress Changed Handler
 */
public interface OnProgress {
    /**
     * Changed Handler
     *
     * @param proceed  Download bytes count
     * @param total    Total bytes count
     * @param progress Download Percentage
     **/
    public void Progress(int proceed, int total, double progress);
}
