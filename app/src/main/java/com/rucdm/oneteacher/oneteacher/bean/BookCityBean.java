package com.rucdm.oneteacher.oneteacher.bean;

import java.io.Serializable;
import java.util.List;

public class BookCityBean implements Serializable {

	private static final long serialVersionUID = -8956384607962766516L;

	public int count;
	public List<BookCityData> data;
	public int error;
	public String msg;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<BookCityData> getData() {
		return data;
	}

	public void setData(List<BookCityData> data) {
		this.data = data;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public class BookCityData {

		public String addtime;
		public String author;
		public String bookabstract;
		public int bookid;
		public String bookname;
		public String bookpic;
		public int booktype;
		public int chaptercount;
		public String classcode;
		public String classname;
		public int customid;
		public String endtime;
		public boolean isauthorizespread;
		public boolean isindex;
		public boolean isvisible;
		public int mid;
		public int number;
		public int opentype;
		public int pagecount;
		public int price;
		public String publisher;
		public int secret;
		public int sort;
		public String source;
		public String starttime;
		public int viewcount;

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

		public String getBookabstract() {
			return bookabstract;
		}

		public void setBookabstract(String bookabstract) {
			this.bookabstract = bookabstract;
		}

		public int getBookid() {
			return bookid;
		}

		public void setBookid(int bookid) {
			this.bookid = bookid;
		}

		public String getBookname() {
			return bookname;
		}

		public void setBookname(String bookname) {
			this.bookname = bookname;
		}

		public String getBookpic() {
			return bookpic;
		}

		public void setBookpic(String bookpic) {
			this.bookpic = bookpic;
		}

		public int getBooktype() {
			return booktype;
		}

		public void setBooktype(int booktype) {
			this.booktype = booktype;
		}

		public int getChaptercount() {
			return chaptercount;
		}

		public void setChaptercount(int chaptercount) {
			this.chaptercount = chaptercount;
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

		public int getCustomid() {
			return customid;
		}

		public void setCustomid(int customid) {
			this.customid = customid;
		}

		public String getEndtime() {
			return endtime;
		}

		public void setEndtime(String endtime) {
			this.endtime = endtime;
		}

		public boolean isIsauthorizespread() {
			return isauthorizespread;
		}

		public void setIsauthorizespread(boolean isauthorizespread) {
			this.isauthorizespread = isauthorizespread;
		}

		public boolean isIsindex() {
			return isindex;
		}

		public void setIsindex(boolean isindex) {
			this.isindex = isindex;
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

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public int getOpentype() {
			return opentype;
		}

		public void setOpentype(int opentype) {
			this.opentype = opentype;
		}

		public int getPagecount() {
			return pagecount;
		}

		public void setPagecount(int pagecount) {
			this.pagecount = pagecount;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public String getPublisher() {
			return publisher;
		}

		public void setPublisher(String publisher) {
			this.publisher = publisher;
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

		public int getViewcount() {
			return viewcount;
		}

		public void setViewcount(int viewcount) {
			this.viewcount = viewcount;
		}

	}
}
