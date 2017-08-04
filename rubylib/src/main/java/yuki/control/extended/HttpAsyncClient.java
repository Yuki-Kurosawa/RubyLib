package yuki.control.extended;

import android.app.Activity;

import java.io.*;
import java.net.*;
import java.util.Scanner;


/*Http Async Client*/
public final class HttpAsyncClient {

    /**
     * Async Get String
     *
     * @param Url      URL
     * @param activity Window
     * @param get      Response Handler
     **/
    public static void AsyncGetString(final String Url, final Activity activity, final OnGet get) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL(Url);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    final String result = readInStream(in);
                    if (get != null && activity != null && get instanceof OnGetString) {
                        activity.runOnUiThread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        ((OnGetString) get).Get(result);
                                    }
                                }
                        );
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                }
            }
        }).start();

    }

    private static String readInStream(InputStream in) {
        Scanner scanner = new Scanner(in).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    /**
     * Async Get File to local path
     *
     * @param Url          File to download
     * @param activity     Window
     * @param savePath     local save path
     * @param saveFileName local file name
     * @param progress     Progress Changed Handler
     * @param get          Download Finished Handler
     **/
    public static void AsyncGetFile(final String Url, final Activity activity, final String savePath,
                                    final String saveFileName, final OnProgress progress, final OnGet get) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(Url);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    final int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();
                    File file = new File(savePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    final String apkFile = savePath + "/" + saveFileName;
                    File ApkFile = new File(apkFile);
                    FileOutputStream fos = new FileOutputStream(ApkFile);
                    int count = 0;
                    final byte[] buf = new byte[1024];
                    //double progressu = 0;
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        final int proceed = count;
                        final double progressu = (double) (((float) count / length) * 100);
                        if (activity != null && progress != null) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progress.Progress(proceed, length, progressu);
                                }
                            });
                        }
                        if (numread <= 0) {

                            //mHandler.sendEmptyMessage(DOWN_OVER);
                            if (activity != null && get != null && get instanceof OnGetFile) {
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((OnGetFile) get).Get(apkFile);
                                    }
                                });
                            }
                            break;
                        }
                        fos.write(buf, 0, numread);
                    } while (true);
                    fos.close();
                    is.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Async Get File to local path (filename auto-detected)
     *
     * @param Url      File to download
     * @param activity Window
     * @param savePath local save path
     * @param progress Progress Changed Handler
     * @param get      Download Finished Handler
     **/
    public static void AsyncGetFile(final String Url, final Activity activity, final String savePath
            , final OnProgress progress, final OnGet get) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(Url);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    final int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();
                    File file = new File(savePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    final String saveFileName = URLDecoder.decode(url.getFile(), "UTF-8");
                    final String apkFile = savePath + "/" + saveFileName;
                    File ApkFile = new File(apkFile);
                    FileOutputStream fos = new FileOutputStream(ApkFile);
                    int count = 0;
                    final byte[] buf = new byte[1024];
                    //double progressu = 0;
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        final int proceed = count;
                        final double progressu = (double) (((float) count / length) * 100);
                        if (activity != null && progress != null) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progress.Progress(proceed, length, progressu);
                                }
                            });
                        }
                        if (numread <= 0) {
                            //mHandler.sendEmptyMessage(DOWN_OVER);
                            if (activity != null && get != null && get instanceof OnGetFile) {
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ((OnGetFile) get).Get(apkFile);
                                    }
                                });
                            }
                            break;
                        }
                        fos.write(buf, 0, numread);
                    } while (true);
                    fos.close();
                    is.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void AsyncPostString(final String Url,final Activity activity,final String ContentType,final String rawData,final OnPost post){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL(Url);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setDoOutput(true);
                    urlConnection.setDoInput(true);
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type",ContentType);
                    urlConnection.setRequestProperty("Connection","Keep-Alive");
                    urlConnection.setRequestProperty("Charset", "UTF-8");
                    urlConnection.setUseCaches(false);
                    OutputStream out=urlConnection.getOutputStream();
                    out.write(rawData.getBytes("UTF-8"));
                    out.flush();
                    out.close();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    final String result = readInStream(in);
                    if (post != null && activity != null && post instanceof OnPostString) {
                        activity.runOnUiThread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        ((OnPostString) post).Post(result);
                                    }
                                }
                        );
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                }
            }
        }).start();
    }

    public static void AsyncRESTString(final String Url,final Activity activity,final String RESTMethod,final String ContentType,final String rawData,final OnREST post){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL(Url);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setDoOutput(true);
                    urlConnection.setDoInput(true);
                    urlConnection.setRequestMethod(RESTMethod);
                    urlConnection.setRequestProperty("Content-Type",ContentType);
                    urlConnection.setRequestProperty("Connection","Keep-Alive");
                    urlConnection.setRequestProperty("Charset", "UTF-8");
                    urlConnection.setUseCaches(false);
                    OutputStream out=urlConnection.getOutputStream();
                    out.write(rawData.getBytes("UTF-8"));
                    out.flush();
                    out.close();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    final String result = readInStream(in);
                    if (post != null && activity != null && post instanceof OnRESTString) {
                        activity.runOnUiThread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        ((OnRESTString) post).REST(result);
                                    }
                                }
                        );
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                }
            }
        }).start();
    }
}
