package com.ltybd.entity;

import com.ltybd.common.domain.MQBody;

/**
 * @zhouyongbo 2017/10/30
 * MQ 消息定义
 * 支付记录详细信息
 */
public class PayRecord implements MQBody{
    private String key; // 唯一标识符
    private  Integer dev_id;//设备ID
    private  String  city_code;//城市编号
    private Integer line_id;//线路ID
    private Integer tdc_flag;//TDC标志
    private String  v_card_code;//虚拟卡号
    private Long up_time;//交易时间
    private String verify_code;//验证码
    private Integer bus_station_code;//城市公交站点编码
    private Integer  bus_station_no;//站点序号
    private String driver_id;//司机编号
    private Integer vehicle_id;//车辆编号
    private Integer bus_req;//公交班次序号
    private Integer onbus_flag;//0,上车；1，下车；
    private Integer direction;//0,上行；1，下行；
    private Long gps_time;//GPS时间
    private Integer lon;//经度
    private Integer lat;//纬度
    private Integer vec1;//速度，指卫星定位车载终端设备上传的行车速度信息，为必填项。单位为千米每小时(km/h)。
    private Integer Vec2;//行驶记录速度，指车辆行驶记录设备上传的行车速度信息。单位为千米每小时(km/h)。
    private Integer distance;//车辆当日总里程数，指车辆上传的行车里程数。单位为米(m)。终端设备每天开机时候清零
    private Integer angle;//方向，0-359，单位为度(°)，正北为0，顺时针。
    private Integer altitude;//海拔高度，单位为米(m)。
    private Integer dis_next;//距离下站距离，单位米（m）
    private Integer time_next;//距离下站的时间，单位秒（s）
    private Integer next_station_no;//下一个站点序号


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getDev_id() {
        return dev_id;
    }

    public void setDev_id(Integer dev_id) {
        this.dev_id = dev_id;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public Integer getLine_id() {
        return line_id;
    }

    public void setLine_id(Integer line_id) {
        this.line_id = line_id;
    }

    public Integer getTdc_flag() {
        return tdc_flag;
    }

    public void setTdc_flag(Integer tdc_flag) {
        this.tdc_flag = tdc_flag;
    }

    public String getV_card_code() {
        return v_card_code;
    }

    public void setV_card_code(String v_card_code) {
        this.v_card_code = v_card_code;
    }

    public Long getUp_time() {
        return up_time;
    }

    public void setUp_time(Long up_time) {
        this.up_time = up_time;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }

    public Integer getBus_station_code() {
        return bus_station_code;
    }

    public void setBus_station_code(Integer bus_station_code) {
        this.bus_station_code = bus_station_code;
    }

    public Integer getBus_station_no() {
        return bus_station_no;
    }

    public void setBus_station_no(Integer bus_station_no) {
        this.bus_station_no = bus_station_no;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public Integer getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Integer vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public Integer getBus_req() {
        return bus_req;
    }

    public void setBus_req(Integer bus_req) {
        this.bus_req = bus_req;
    }

    public Integer getOnbus_flag() {
        return onbus_flag;
    }

    public void setOnbus_flag(Integer onbus_flag) {
        this.onbus_flag = onbus_flag;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Long getGps_time() {
        return gps_time;
    }

    public void setGps_time(Long gps_time) {
        this.gps_time = gps_time;
    }

    public Integer getLon() {
        return lon;
    }

    public void setLon(Integer lon) {
        this.lon = lon;
    }

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    public Integer getVec1() {
        return vec1;
    }

    public void setVec1(Integer vec1) {
        this.vec1 = vec1;
    }

    public Integer getVec2() {
        return Vec2;
    }

    public void setVec2(Integer vec2) {
        Vec2 = vec2;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getAngle() {
        return angle;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Integer getDis_next() {
        return dis_next;
    }

    public void setDis_next(Integer dis_next) {
        this.dis_next = dis_next;
    }

    public Integer getTime_next() {
        return time_next;
    }

    public void setTime_next(Integer time_next) {
        this.time_next = time_next;
    }

    public Integer getNext_station_no() {
        return next_station_no;
    }

    public void setNext_station_no(Integer next_station_no) {
        this.next_station_no = next_station_no;
    }
}
