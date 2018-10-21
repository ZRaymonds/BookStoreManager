package com.app.bookstoremanager.bean;


import java.util.List;

public class UpdateInfo {

    /**
     * code : 200
     * data : {"page_num":1,"page_size":10,"size":1,"total":1,"pages":1,"list":[{"id":25,"version_code":2,"file_name":"app-release.apk","file_url":"/app/download/LM/20181015110722307_890726.apk","developer":"lm","turnover_time":"2018-10-15 11:07:22"}]}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * page_num : 1
         * page_size : 10
         * size : 1
         * total : 1
         * pages : 1
         * list : [{"id":25,"version_code":2,"file_name":"app-release.apk","file_url":"/app/download/LM/20181015110722307_890726.apk","developer":"lm","turnover_time":"2018-10-15 11:07:22"}]
         */

        private int page_num;
        private int page_size;
        private int size;
        private int total;
        private int pages;
        private List<ListBean> list;

        public int getPage_num() {
            return page_num;
        }

        public void setPage_num(int page_num) {
            this.page_num = page_num;
        }

        public int getPage_size() {
            return page_size;
        }

        public void setPage_size(int page_size) {
            this.page_size = page_size;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }


        public static class ListBean {
            /**
             * id : 25
             * version_code : 2
             * file_name : app-release.apk
             * file_url : /app/download/LM/20181015110722307_890726.apk
             * developer : lm
             * turnover_time : 2018-10-15 11:07:22
             */

            private int id;
            private int version_code;
            private String file_name;
            private String file_url;
            private String developer;
            private String turnover_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getVersion_code() {
                return version_code;
            }

            public void setVersion_code(int version_code) {
                this.version_code = version_code;
            }

            public String getFile_name() {
                return file_name;
            }

            public void setFile_name(String file_name) {
                this.file_name = file_name;
            }

            public String getFile_url() {
                return file_url;
            }

            public void setFile_url(String file_url) {
                this.file_url = file_url;
            }

            public String getDeveloper() {
                return developer;
            }

            public void setDeveloper(String developer) {
                this.developer = developer;
            }

            public String getTurnover_time() {
                return turnover_time;
            }

            public void setTurnover_time(String turnover_time) {
                this.turnover_time = turnover_time;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "id=" + id +
                        ", version_code=" + version_code +
                        ", file_name='" + file_name + '\'' +
                        ", file_url='" + file_url + '\'' +
                        ", developer='" + developer + '\'' +
                        ", turnover_time='" + turnover_time + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "page_num=" + page_num +
                    ", page_size=" + page_size +
                    ", size=" + size +
                    ", total=" + total +
                    ", pages=" + pages +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UpdateInfo{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
