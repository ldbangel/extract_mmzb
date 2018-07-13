package com.kejin.extract.domainservice.growingio;

import java.util.List;

public class ChartBean {

    private String id;
    private String name;
    private long startTime;
    private long endTime;
    private long interval;
    private List<Meta> meta;
    private List<List<String>> data;
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setStartTime(long startTime) {
         this.startTime = startTime;
     }
     public long getStartTime() {
         return startTime;
     }

    public void setEndTime(long endTime) {
         this.endTime = endTime;
     }
     public long getEndTime() {
         return endTime;
     }

    public void setInterval(long interval) {
         this.interval = interval;
     }
     public long getInterval() {
         return interval;
     }

    public void setMeta(List<Meta> meta) {
         this.meta = meta;
     }
     public List<Meta> getMeta() {
         return meta;
     }

    public void setData(List<List<String>> data) {
         this.data = data;
     }
     public List<List<String>> getData() {
         return data;
     }
     
    public class Meta {

        private String name;
        private boolean dimension;
        public void setName(String name) {
             this.name = name;
         }
         public String getName() {
             return name;
         }

        public void setDimension(boolean dimension) {
             this.dimension = dimension;
         }
         public boolean getDimension() {
             return dimension;
         }

    }
}
