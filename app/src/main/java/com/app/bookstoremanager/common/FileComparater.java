package com.app.bookstoremanager.common;

import com.app.bookstoremanager.bean.ScanBook;

import java.io.File;
import java.util.Comparator;

/**
 * Created by tctctc on 2017/3/20.
 */

public class FileComparater implements Comparator<ScanBook> {
    @Override
    public int compare(ScanBook lhsBook, ScanBook rhsBook) {
        File lhs = lhsBook.getFile();
        File rhs = rhsBook.getFile();
        try {
            if (lhs.isFile() && rhs.isFile()) {
                return lhs.length() > rhs.length() ? -1 : 1;
            } else if (!lhs.isFile() && rhs.isFile()) {
                return 1;
            } else if (lhs.isFile() && !rhs.isFile()) {
                return -1;
            } else {
                return lhs.getName().compareTo(rhs.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
