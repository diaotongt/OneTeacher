package com.rucdm.oneteacher.oneteacher.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/14.
 */


public class LogInSbBean implements Serializable {

    private static final long serialVersionUID = -3462146314449568504L;
    public LogInData data;

    @Override
    public String toString() {
        return "LogInBean [data=" + data + ", error=" + error + ", msg=" + msg
                + ", session_token=" + session_token + "]";
    }

    public LogInData getData() {
        return data;
    }

    public void setData(LogInData data) {
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

    public String getSession_token() {
        return session_token;
    }

    public void setSession_token(String session_token) {
        this.session_token = session_token;
    }

    public int error;
    public String msg;
    public String session_token;

    public class LogInData {

        @Override
        public String toString() {

            return "LogInData [addtime=" + addtime + ", appopenid=" + appopenid
                    + ", avater=" + avater + ", checkpicture=" + checkpicture
                    + ", city=" + city + ", comefrom=" + comefrom
                    + ", fanspagelogo=" + fanspagelogo + ", isavalable="
                    + isavalable + ", iscomplete=" + iscomplete + ", isdelete="
                    + isdelete + ", isjoinnotify=" + isjoinnotify
                    + ", isreadguidepage=" + isreadguidepage + ", isrecommend="
                    + isrecommend + ", isverifyphone=" + isverifyphone
                    + ", mid=" + mid + ", msgpagelogo=" + msgpagelogo
                    + ", nickname=" + nickname + ", password=" + password
                    + ", phone=" + phone + ", province=" + province
                    + ", ranklevel=" + ranklevel + ", showcard=" + showcard
                    + ", states=" + states + ", sxpagelogo=" + sxpagelogo
                    + ", username=" + username + ", valifyinfo=" + valifyinfo
                    + ", vipexperience=" + vipexperience + ", weixin=" + weixin
                    + ", wxid=" + wxid + ", wxunionid=" + wxunionid + "]";
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getAppopenid() {
            return appopenid;
        }

        public void setAppopenid(String appopenid) {
            this.appopenid = appopenid;
        }

        public String getAvater() {
            return avater;
        }

        public void setAvater(String avater) {
            this.avater = avater;
        }

        public int getCheckpicture() {
            return checkpicture;
        }

        public void setCheckpicture(int checkpicture) {
            this.checkpicture = checkpicture;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getComefrom() {
            return comefrom;
        }

        public void setComefrom(String comefrom) {
            this.comefrom = comefrom;
        }

        public boolean isFanspagelogo() {
            return fanspagelogo;
        }

        public void setFanspagelogo(boolean fanspagelogo) {
            this.fanspagelogo = fanspagelogo;
        }

        public boolean isIsavalable() {
            return isavalable;
        }

        public void setIsavalable(boolean isavalable) {
            this.isavalable = isavalable;
        }

        public boolean isIscomplete() {
            return iscomplete;
        }

        public void setIscomplete(boolean iscomplete) {
            this.iscomplete = iscomplete;
        }

        public boolean isIsdelete() {
            return isdelete;
        }

        public void setIsdelete(boolean isdelete) {
            this.isdelete = isdelete;
        }

        public int getIsjoinnotify() {
            return isjoinnotify;
        }

        public void setIsjoinnotify(int isjoinnotify) {
            this.isjoinnotify = isjoinnotify;
        }

        public boolean isIsreadguidepage() {
            return isreadguidepage;
        }

        public void setIsreadguidepage(boolean isreadguidepage) {
            this.isreadguidepage = isreadguidepage;
        }

        public boolean isIsrecommend() {
            return isrecommend;
        }

        public void setIsrecommend(boolean isrecommend) {
            this.isrecommend = isrecommend;
        }

        public boolean isIsverifyphone() {
            return isverifyphone;
        }

        public void setIsverifyphone(boolean isverifyphone) {
            this.isverifyphone = isverifyphone;
        }

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public boolean isMsgpagelogo() {
            return msgpagelogo;
        }

        public void setMsgpagelogo(boolean msgpagelogo) {
            this.msgpagelogo = msgpagelogo;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public int getRanklevel() {
            return ranklevel;
        }

        public void setRanklevel(int ranklevel) {
            this.ranklevel = ranklevel;
        }

        public int getShowcard() {
            return showcard;
        }

        public void setShowcard(int showcard) {
            this.showcard = showcard;
        }

        public String getStates() {
            return states;
        }

        public void setStates(String states) {
            this.states = states;
        }

        public boolean isSxpagelogo() {
            return sxpagelogo;
        }

        public void setSxpagelogo(boolean sxpagelogo) {
            this.sxpagelogo = sxpagelogo;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getValifyinfo() {
            return valifyinfo;
        }

        public void setValifyinfo(String valifyinfo) {
            this.valifyinfo = valifyinfo;
        }

        public int getVipexperience() {
            return vipexperience;
        }

        public void setVipexperience(int vipexperience) {
            this.vipexperience = vipexperience;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getWxid() {
            return wxid;
        }

        public void setWxid(String wxid) {
            this.wxid = wxid;
        }

        public String getWxunionid() {
            return wxunionid;
        }

        public void setWxunionid(String wxunionid) {
            this.wxunionid = wxunionid;
        }

        public String getTreecode() {
            return treecode;
        }

        public void setTreecode(String treecode) {
            this.treecode = treecode;
        }

        public double getAccuracy() {
            return accuracy;
        }

        public void setAccuracy(double accuracy) {
            this.accuracy = accuracy;
        }

        public String getAcountvalue() {
            return acountvalue;
        }

        public void setAcountvalue(String acountvalue) {
            this.acountvalue = acountvalue;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getClaimpagetime() {
            return claimpagetime;
        }

        public void setClaimpagetime(String claimpagetime) {
            this.claimpagetime = claimpagetime;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCompletedate() {
            return completedate;
        }

        public void setCompletedate(String completedate) {
            this.completedate = completedate;
        }

        public String getCompletetitlesdate() {
            return completetitlesdate;
        }

        public void setCompletetitlesdate(String completetitlesdate) {
            this.completetitlesdate = completetitlesdate;
        }

        public String getCompost() {
            return compost;
        }

        public void setCompost(String compost) {
            this.compost = compost;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getFanspagetime() {
            return fanspagetime;
        }

        public void setFanspagetime(String fanspagetime) {
            this.fanspagetime = fanspagetime;
        }

        public String getIntroduct() {
            return introduct;
        }

        public void setIntroduct(String introduct) {
            this.introduct = introduct;
        }

        public String getIsshowsecrect() {
            return isshowsecrect;
        }

        public void setIsshowsecrect(String isshowsecrect) {
            this.isshowsecrect = isshowsecrect;
        }

        public boolean isIssubscribe() {
            return issubscribe;
        }

        public void setIssubscribe(boolean issubscribe) {
            this.issubscribe = issubscribe;
        }

        public int getIsvarify() {
            return isvarify;
        }

        public void setIsvarify(int isvarify) {
            this.isvarify = isvarify;
        }

        public String getLastpushtime() {
            return lastpushtime;
        }

        public void setLastpushtime(String lastpushtime) {
            this.lastpushtime = lastpushtime;
        }

        public String getLevelrange() {
            return levelrange;
        }

        public void setLevelrange(String levelrange) {
            this.levelrange = levelrange;
        }

        public String getLogintime() {
            return logintime;
        }

        public void setLogintime(String logintime) {
            this.logintime = logintime;
        }

        public double getMaplatitude() {
            return maplatitude;
        }

        public void setMaplatitude(double maplatitude) {
            this.maplatitude = maplatitude;
        }

        public double getMaplongitude() {
            return maplongitude;
        }

        public void setMaplongitude(double maplongitude) {
            this.maplongitude = maplongitude;
        }

        public String getMsgpagetime() {
            return msgpagetime;
        }

        public void setMsgpagetime(String msgpagetime) {
            this.msgpagetime = msgpagetime;
        }

        public String getOldname() {
            return oldname;
        }

        public void setOldname(String oldname) {
            this.oldname = oldname;
        }

        public String getPatent() {
            return patent;
        }

        public void setPatent(String patent) {
            this.patent = patent;
        }

        public String getPatentnum() {
            return patentnum;
        }

        public void setPatentnum(String patentnum) {
            this.patentnum = patentnum;
        }

        public String getPrising() {
            return prising;
        }

        public void setPrising(String prising) {
            this.prising = prising;
        }

        public String getPrisingnum() {
            return prisingnum;
        }

        public void setPrisingnum(String prisingnum) {
            this.prisingnum = prisingnum;
        }

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public String getPubbook() {
            return pubbook;
        }

        public void setPubbook(String pubbook) {
            this.pubbook = pubbook;
        }

        public String getPy() {
            return py;
        }

        public void setPy(String py) {
            this.py = py;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getScorevalue() {
            return scorevalue;
        }

        public void setScorevalue(String scorevalue) {
            this.scorevalue = scorevalue;
        }

        public String getSearchfield() {
            return searchfield;
        }

        public void setSearchfield(String searchfield) {
            this.searchfield = searchfield;
        }

        public String getSocpost() {
            return socpost;
        }

        public void setSocpost(String socpost) {
            this.socpost = socpost;
        }

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public String getStartime() {
            return startime;
        }

        public void setStartime(String startime) {
            this.startime = startime;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getSubscribetime() {
            return subscribetime;
        }

        public void setSubscribetime(String subscribetime) {
            this.subscribetime = subscribetime;
        }

        public String getTitles() {
            return titles;
        }

        public void setTitles(String titles) {
            this.titles = titles;
        }

        public int getTitlestype() {
            return titlestype;
        }

        public void setTitlestype(int titlestype) {
            this.titlestype = titlestype;
        }

        public String getTreecodesub() {
            return treecodesub;
        }

        public void setTreecodesub(String treecodesub) {
            this.treecodesub = treecodesub;
        }

        public String getUnsubscribetime() {
            return unsubscribetime;
        }

        public void setUnsubscribetime(String unsubscribetime) {
            this.unsubscribetime = unsubscribetime;
        }

        public String getValifycompany() {
            return valifycompany;
        }

        public void setValifycompany(String valifycompany) {
            this.valifycompany = valifycompany;
        }

        public String getValifyinsinfo() {
            return valifyinsinfo;
        }

        public void setValifyinsinfo(String valifyinsinfo) {
            this.valifyinsinfo = valifyinsinfo;
        }

        public String getValifyname() {
            return valifyname;
        }

        public void setValifyname(String valifyname) {
            this.valifyname = valifyname;
        }

        public String getValifypdc() {
            return valifypdc;
        }

        public void setValifypdc(String valifypdc) {
            this.valifypdc = valifypdc;
        }

        public String getValifytitleinfo() {
            return valifytitleinfo;
        }

        public void setValifytitleinfo(String valifytitleinfo) {
            this.valifytitleinfo = valifytitleinfo;
        }

        public String getValifytype() {
            return valifytype;
        }

        public void setValifytype(String valifytype) {
            this.valifytype = valifytype;
        }

        public int getVipnumber() {
            return vipnumber;
        }

        public void setVipnumber(int vipnumber) {
            this.vipnumber = vipnumber;
        }

        public String getWebopenid() {
            return webopenid;
        }

        public void setWebopenid(String webopenid) {
            this.webopenid = webopenid;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getWorked() {
            return worked;
        }

        public void setWorked(String worked) {
            this.worked = worked;
        }

        public String getWorkexperience() {
            return workexperience;
        }

        public void setWorkexperience(String workexperience) {
            this.workexperience = workexperience;
        }

        public String getZipecode() {
            return zipecode;
        }

        public void setZipecode(String zipecode) {
            this.zipecode = zipecode;
        }

        public double accuracy;
        public String acountvalue;
        public String address;
        public String addtime;
        public String appopenid;
        public String avater;
        public int checkpicture;
        public String city;
        public String claimpagetime;
        public String comefrom;
        public String company;
        public String completedate;
        public String completetitlesdate;
        public String compost;
        public String county;
        public String department;
        public String email;
        public String endtime;
        public boolean fanspagelogo;
        public String fanspagetime;
        public String introduct;
        public boolean isavalable;
        public boolean iscomplete;
        public boolean isdelete;
        public int isjoinnotify;
        public boolean isreadguidepage;
        public boolean isrecommend;
        public String isshowsecrect;
        public boolean issubscribe;
        public int isvarify;
        public boolean isverifyphone;
        public String lastpushtime;
        public String levelrange;
        public String logintime;
        public double maplatitude;
        public double maplongitude;
        public int mid;
        public boolean msgpagelogo;
        public String msgpagetime;
        public String nickname;
        public String oldname;
        public String password;
        public String patent;
        public String patentnum;
        public String phone;
        public String prising;
        public String prisingnum;
        public String project;
        public String province;
        public String pubbook;
        public String py;
        public String qq;
        public int ranklevel;
        public String realname;
        public String scorevalue;
        public String searchfield;
        public int showcard;
        public String socpost;
        public double speed;
        public String startime;
        public String states;
        public String street;
        public String subscribetime;
        public boolean sxpagelogo;
        public String titles;
        public int titlestype;
        public String treecode;
        public String treecodesub;
        public String unsubscribetime;
        public String username;
        public String valifycompany;
        public String valifyinfo;
        public String valifyinsinfo;
        public String valifyname;
        public String valifypdc;
        public String valifytitleinfo;
        public String valifytype;
        public int vipexperience;
        public int vipnumber;
        public String webopenid;
        public String website;
        public String weixin;
        public String worked;
        public String workexperience;
        public String wxid;
        public String wxunionid;
        public String zipecode;
    }
}
