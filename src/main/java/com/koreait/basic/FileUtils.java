package com.koreait.basic;

import java.io.File;

public class FileUtils {


    //폴더 삭제
    public static void delFolder(String path){
        delFolderFiles(path, true);
    }


    //폴더 아래 파일만 삭제
    //폴더 경로, 폴더더도 삭제할것인가 ? true > yes.
    public static void delFolderFiles(String path, boolean isDelFolder){
        File folder = new File(path);
        if(folder.exists() && folder.isDirectory()){ //존재하니? && 폴더니?
            File[] fileList = folder.listFiles(); //폴더안의 폴더or파일 을 배열로 나열
            if(fileList == null || fileList.length == 0){
                return; // 폴더 아래에 파일이 없음을 의미 > 그럼 종료
            }
            for(File f : fileList){
                if(f.isDirectory()){
                    delFolderFiles(f.getPath(), true);
                }else {
                    f.delete();
                }
            }
            if(isDelFolder){folder.delete();}
        }
    }


}
