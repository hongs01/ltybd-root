package com.ltybd;

import com.ltybd.PayStateMachine.properties.PayProperties;
import com.ltybd.PayStateMachine.properties.ServiceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.*;
import java.util.*;
/**
 * @author zhouyongbo
 * 为方便集群分布式配置
 */
@Configurable
@Component
public class MyProperties {
    private static Logger logger = LoggerFactory.getLogger(MyProperties.class);


    @Autowired
    private PayProperties payProperties;

    @Value("${server.port}")
    private String port;



    @Bean
    public ServiceProperties getMQPropertis()  {
        String serviceId = null;
        try {
            serviceId = getServiceId();
        } catch (SocketException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        if (serviceId == null){
            throw new RuntimeException("servicesIds配置错误 或 servicesIds 与本机IP不符");
        }
        ServiceProperties serviceProperties =  new ServiceProperties() {

        };
        logger.info("本服务 serviceId:" + serviceId);
        serviceProperties.setServiceId(serviceId);
        return serviceProperties;
    }


    /**
     * 获取service Id
     * @return
     * @throws SocketException
     */
    private String getServiceId() throws SocketException {
        Map<String, List<String>> serviceIds = payProperties.getServiceIds();
        if (serviceIds == null || serviceIds.size() == 0){
            throw new RuntimeException(" 需要配置serviceIds");
        }
        List<String> ipv4Address = getIpv4Address();
        if (ipv4Address == null || ipv4Address.size() == 0){
            port = port== null?port :"8080";
            logger.error("无法获取本地IP地址,默认地址为127.0.0.1:"+port);
            ipv4Address = new ArrayList<String>();
            ipv4Address.add( "127.0.0.1:"+port);
        }

        for (String key:serviceIds.keySet()){
            List<String> values = serviceIds.get(key);
            if (values == null || values.size() == 0){
                continue;
            }
            for (String value:values){
                String[] split = value.split(":",2);
                //主要处理参数配置不正确   等于1 就是没有端口号  默认为本服务的端口号
                // 大于2 就是有多余的不正确参数  只取前两位
                if (split.length == 1){
                    split= new String[] {split[0],port};
                }else if (split.length > 2){
                    split = new String[]{split[0], split[1]};
                }
                boolean contains = ipv4Address.contains(split[0]);
                if(contains && port.equals(split[1])){
                    return key;
                }
            }
        }
//        InetAddress addr = InetAddress.getLocalHost();
//        String hostName=addr.getHostName().toString();
        return null;
    }

    /**
     * 获取IPV4 地址集合
     * @return
     * @throws SocketException
     */
    private List<String> getIpv4Address() throws SocketException {
        List<String> strings = new ArrayList<String>();
        //get all local ips
        Enumeration<NetworkInterface> interfs = NetworkInterface.getNetworkInterfaces();
        while (interfs.hasMoreElements()) {
            NetworkInterface interf = interfs.nextElement();
            Enumeration<InetAddress> addres = interf.getInetAddresses();
            while (addres.hasMoreElements()) {
                InetAddress in = addres.nextElement();
                if (in instanceof Inet4Address) {
                    strings.add(in.getHostAddress());
                } else if (in instanceof Inet6Address) {
                }
            }
        }
        return strings;
    }
}
