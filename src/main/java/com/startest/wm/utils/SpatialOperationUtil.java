package com.startest.wm.utils;

import com.startest.wm.enums.EnumCoordType;

/**
 * @公司 星天科技
 * @作者 王华伟
 * @创建时间 2020-04-09 18:55
 * @描述 空间数据操作类
 **/
public class SpatialOperationUtil {

    public static String FormatCoordToString(String coordStr, EnumCoordType enumCoordType) {
        double coord = 0.0;
        switch (enumCoordType) {
            case DMSS:
                coord = GetCoordDMSS(coordStr);
                break;
            case DMS:
                coord = GetCoordDMS(coordStr);
                break;
            case DM:
                coord = GetCoordDM(coordStr);
                break;
            case DD:
                coord = GetCoordDD(coordStr);
                break;
        }
        return coordStr;
    }


    /**
     * @Description: 将DMSS格式的坐标转换成double类型
     * @Param: [coord]
     * @return: double
     **/
    public static double GetCoordDMSS(String coord) {
        double coordinate = 0.0;
        char jwType = coord.charAt(coord.length() - 1);
        if (coord.indexOf('.') != -1) {
            String coordStr = (coord.substring(0, coord.length() - 2));
            String[] dmsArray = coordStr.split("°|′|″");
            coordinate = Double.parseDouble(dmsArray[0]) + Double.parseDouble(dmsArray[1]) / 60 + Double.parseDouble(dmsArray[2]) / 3600 + Double.parseDouble(dmsArray[3]) / 3600;
        }
        if (jwType == 'S' || jwType == 'W') {
            coordinate = -coordinate;
        }
        return coordinate;
    }

    /**
     * @Description: 将DMS格式的坐标转换成double类型
     * @Param: [coord]
     * @return: double
     **/
    public static double GetCoordDMS(String coord) {
        double coordinate = 0.0;
        char jwType = coord.charAt(coord.length() - 1);
        String coordStr = (coord.substring(0, coord.length() - 2));
        String[] dmsArray = coordStr.split("°|′|″");
        coordinate = Double.parseDouble(dmsArray[0]) + Double.parseDouble(dmsArray[1]) / 60 + Double.parseDouble(dmsArray[2]) / 3600;
        if (jwType == 'S' || jwType == 'W') {
            coordinate = -coordinate;
        }
        return coordinate;
    }

    /**
     * @Description: 将DM格式的坐标转换成double类型
     * @Param: [coord]
     * @return: double
     **/
    public static double GetCoordDM(String coord) {
        double coordinate = 0.0;
        char jwType = coord.charAt(coord.length() - 1);
        String coordStr = (coord.substring(0, coord.length() - 2));
        String[] dmsArray = coordStr.split("°");
        coordinate = Double.parseDouble(dmsArray[0]) + Double.parseDouble(dmsArray[1]) / 60;
        if (jwType == 'S' || jwType == 'W') {
            coordinate = -coordinate;
        }
        return coordinate;
    }

    /**
     * @Description: 将DD格式的坐标转换成double类型
     * @Param: [coord]
     * @return: double
     **/
    public static double GetCoordDD(String coord) {
        double coordinate = 0.0;
        char jwType = coord.charAt(coord.length() - 1);
        String coordStr = (coord.substring(0, coord.length() - 2));
        coordinate = Double.parseDouble(coordStr);
        if (jwType == 'S' || jwType == 'W') {
            coordinate = -coordinate;
        }
        return coordinate;
    }


    /**
     * @Description: 度分秒转换成度
     * @Param: [str]
     * @return: java.lang.String
     **/
    public static String dms2d(String str) {
        String wkt;
        int length = str.length();
        Double second = Double.parseDouble(str.substring(length - 2));
        Double minute = Double.parseDouble(str.substring(length - 4, length - 2));
        Double degree = Double.parseDouble(str.substring(0, length - 4));
        if (degree >= 0) {
            Double temp = degree + minute / 60 + second / 3600;
            wkt = String.format("%.9f", temp);
        } else {
            wkt = "-" + String.valueOf(Math.abs(degree) + minute / 60 + second / 3600);
        }
        return wkt;
    }

    //将海图资料中四至属性转换成WKT字符串
    public static String getMapWKT(String nLat, String sLat, String wLon, String eLon) {

        /*
        //POLYGON类型组装
        //组装SRID=4326;POLYGON((85.333333333 16.5000000000001,85.333333333 23.0000000000001,95.4166666670001 23.0000000000001,95.4166666670001 16.5000000000001,85.333333333 16.5000000000001))
        String north = SpatialOperationUtil.dms2d(nLat);
        String south = SpatialOperationUtil.dms2d(sLat);
        String west = SpatialOperationUtil.dms2d(wLon);
        String east = SpatialOperationUtil.dms2d(eLon);
        String WKT = "st_geomfromewkt('SRID=4326;POLYGON(("
                + west + " " + south + ","
                + west + " " + north + ","
                + east + " " + north + ","
                + east + " " + south + ","
                + west + " " + south + ")))'";
        */

        /*
         * 海图资料从西图廓开始到东图廓。如（160 20, 160 10, -170 10, -170 20, 160 20）
         * 考虑到海图有跨180度的问题。因此数据类型不再用POLYGON，改用MULTIPOLYGON，对图廓进行切分
         * 组装MULTIPOLYGON：
         * update map_data SET shape=st_multipolygonfromtext('MULTIPOLYGON(((-180 -10,-170 -10,-170 -20,-180 -20,-180 -10)),((160 -10,180 -10,180 -20,160 -20,160 -10)))', 4326) WHERE map_code='bb'
         */
        String north = SpatialOperationUtil.dms2d(nLat);
        String south = SpatialOperationUtil.dms2d(sLat);
        String west = SpatialOperationUtil.dms2d(wLon);
        String east = SpatialOperationUtil.dms2d(eLon);
        String WKT = "";
        if (Double.parseDouble(wLon) > 0 && Double.parseDouble(eLon) < 0) {
            //图廓范围跨180度，需要进行切分
            WKT = "st_multipolygonfromtext('MULTIPOLYGON((("
                    + west + " " + south + ","
                    + west + " " + north + ","
                    + 180 + " " + north + ","
                    + 180 + " " + south + ","
                    + west + " " + south + ")),(("
                    + -180 + " " + south + ","
                    + -180 + " " + north + ","
                    + east + " " + north + ","
                    + east + " " + south + ","
                    + -180 + " " + south + ")))',4326)";
        } else {
            WKT = "st_multipolygonfromtext('MULTIPOLYGON((("
                    + west + " " + south + ","
                    + west + " " + north + ","
                    + east + " " + north + ","
                    + east + " " + south + ","
                    + west + " " + south + ")))',4326)";
        }
        return WKT;
    }

    //将港口资料中经纬度属性转换成WKT字符串
    public static String getPortWKT(String lon, String lat) {
        //组装insert into test (num,geo)values(1,st_pointfromtext('point(120 20)',4326))
        String x = SpatialOperationUtil.dms2d(lon);
        String y = SpatialOperationUtil.dms2d(lat);
        String WKT = "st_pointfromtext('point(" + x + " " + y + ")',4326)";
        return WKT;
    }
}
