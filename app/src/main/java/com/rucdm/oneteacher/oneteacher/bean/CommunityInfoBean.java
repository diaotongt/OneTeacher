package com.rucdm.oneteacher.oneteacher.bean;

import java.io.Serializable;
import java.util.List;

public class CommunityInfoBean implements Serializable {

	private static final long serialVersionUID = -906261607363067425L;
	public int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<CommunityInfoData> getData() {
		return data;
	}

	public void setData(List<CommunityInfoData> data) {
		this.data = data;
	}

	public List<CommunityInfoData> data;

	public class CommunityInfoData {
		public String addtime;
		public String author;
		public String classcode;
		public String classname;
		public String contents;
		public String discription;
		public int inserttype;
		public boolean isdelete;
		public boolean ishot;
		public boolean isvisible;
		public int mid;
		public int nid;
		public String picture;
		public String publishdate;
		public int secret;
		public int sort;
		public String source;
		public String sourceurl;
		public String starttime;
		public int tabtype;
		public String title;
		public int type;
		public int viewcount;

		public String getSourceurl() {
			return sourceurl;
		}

		public void setSourceurl(String sourceurl) {
			this.sourceurl = sourceurl;
		}

		public String getAddtime() {
			return addtime;
		}

		public void setAddtime(String addtime) {
			this.addtime = addtime;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getClasscode() {
			return classcode;
		}

		public void setClasscode(String classcode) {
			this.classcode = classcode;
		}

		public String getClassname() {
			return classname;
		}

		public void setClassname(String classname) {
			this.classname = classname;
		}

		public String getContents() {
			return contents;
		}

		public void setContents(String contents) {
			this.contents = contents;
		}

		public String getDiscription() {
			return discription;
		}

		public void setDiscription(String discription) {
			this.discription = discription;
		}

		public int getInserttype() {
			return inserttype;
		}

		public void setInserttype(int inserttype) {
			this.inserttype = inserttype;
		}

		public boolean isIsdelete() {
			return isdelete;
		}

		public void setIsdelete(boolean isdelete) {
			this.isdelete = isdelete;
		}

		public boolean isIshot() {
			return ishot;
		}

		public void setIshot(boolean ishot) {
			this.ishot = ishot;
		}

		public boolean isIsvisible() {
			return isvisible;
		}

		public void setIsvisible(boolean isvisible) {
			this.isvisible = isvisible;
		}

		public int getMid() {
			return mid;
		}

		public void setMid(int mid) {
			this.mid = mid;
		}

		public int getNid() {
			return nid;
		}

		public void setNid(int nid) {
			this.nid = nid;
		}

		public String getPicture() {
			return picture;
		}

		public void setPicture(String picture) {
			this.picture = picture;
		}

		public String getPublishdate() {
			return publishdate;
		}

		public void setPublishdate(String publishdate) {
			this.publishdate = publishdate;
		}

		public int getSecret() {
			return secret;
		}

		public void setSecret(int secret) {
			this.secret = secret;
		}

		public int getSort() {
			return sort;
		}

		public void setSort(int sort) {
			this.sort = sort;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getStarttime() {
			return starttime;
		}

		public void setStarttime(String starttime) {
			this.starttime = starttime;
		}

		public int getTabtype() {
			return tabtype;
		}

		public void setTabtype(int tabtype) {
			this.tabtype = tabtype;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getViewcount() {
			return viewcount;
		}

		public void setViewcount(int viewcount) {
			this.viewcount = viewcount;
		}

	
	}
}
