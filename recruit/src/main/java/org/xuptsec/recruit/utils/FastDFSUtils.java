package org.xuptsec.recruit.utils;


import org.csource.fastdfs.*;
import org.csource.common.NameValuePair;
/**
 * Created by mu on 2017/10/15.
 */
public class FastDFSUtils {
    private TrackerClient trackerClient=null;
    private TrackerServer trackerServer=null;
    private StorageClient1 storageClient=null;
    private StorageServer storageServer=null;

    public  FastDFSUtils(String confUrl) throws Exception {
        if(confUrl.contains("classpath:")){
            confUrl=confUrl.replace("classpath:",this.getClass().getResource("/").getPath());

        }

        ClientGlobal.init(confUrl);
        trackerClient=new TrackerClient();
        trackerServer=trackerClient.getConnection();
        storageServer=null;
        storageClient=new StorageClient1(trackerServer,storageServer);
    }

    /**
     * 上传文件，
     * @param fileName 文件流
     * @param extName   扩展名（不加.）
     * @param meta
     * @return
     * @throws Exception
     */
    public String uploadFile(byte[] fileName, String extName, NameValuePair[] meta)throws Exception{
        String result = storageClient.upload_file1(fileName, extName, meta);
        return result;
    }
    /**
     * 上传文件，
     * @param fileName 文件名
     * @param extName   扩展名（不加.）
     * @param meta
     * @return
     * @throws Exception
     */
    public String uploadFile2(String fileName, String extName, NameValuePair[] meta)throws Exception{
        String result = storageClient.upload_file1(fileName, extName, meta);
        return result;
    }

    public static void main(String[] args) throws Exception {
        FastDFSUtils utils = new FastDFSUtils("classpath:fast.properties");
        String s = utils.uploadFile2("C:\\Users\\mu\\Desktop\\TIM截图20171022201503.png", "png", null);
        System.out.println(s);
    }
}
