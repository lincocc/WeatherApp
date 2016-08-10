package com.demo.weatherapp.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherList {

    @SerializedName("HeWeather data service 3.0")
    public List<WeatherBean> heWeatherList;

    public static class WeatherBean {
        @SerializedName("aqi")
        public AqiBean aqi;
        @SerializedName("basic")
        public BasicBean basic;
        @SerializedName("now")
        public NowBean now;
        @SerializedName("status")
        public String status;
        @SerializedName("suggestion")
        public SuggestionBean suggestion;
        @SerializedName("daily_forecast")
        public List<DailyForecastBean> dailyForecast;
        @SerializedName("hourly_forecast")
        public List<HourlyForecastBean> hourlyForecast;

        public static class AqiBean {
            @SerializedName("city")
            public CityBean city;

            public static class CityBean {
                @SerializedName("aqi")
                public String aqi;
                @SerializedName("co")
                public String co;
                @SerializedName("no2")
                public String no2;
                @SerializedName("o3")
                public String o3;
                @SerializedName("pm10")
                public String pm10;
                @SerializedName("pm25")
                public String pm25;
                @SerializedName("qlty")
                public String qlty;
                @SerializedName("so2")
                public String so2;
            }
        }

        public static class BasicBean {
            @SerializedName("city")
            public String city;
            @SerializedName("cnty")
            public String cnty;
            @SerializedName("id")
            public String id;
            @SerializedName("lat")
            public String lat;
            @SerializedName("lon")
            public String lon;
            @SerializedName("update")
            public UpdateBean update;

            public static class UpdateBean {
                @SerializedName("loc")
                public String loc;
                @SerializedName("utc")
                public String utc;
            }
        }

        public static class NowBean {
            @SerializedName("cond")
            public CondBean cond;
            @SerializedName("fl")
            public String fl;
            @SerializedName("hum")
            public String hum;
            @SerializedName("pcpn")
            public String pcpn;
            @SerializedName("pres")
            public String pres;
            @SerializedName("tmp")
            public String tmp;
            @SerializedName("vis")
            public String vis;
            @SerializedName("wind")
            public WindBean wind;

            public static class CondBean {
                @SerializedName("code")
                public String code;
                @SerializedName("txt")
                public String txt;
            }

            public static class WindBean {
                @SerializedName("deg")
                public String deg;
                @SerializedName("dir")
                public String dir;
                @SerializedName("sc")
                public String sc;
                @SerializedName("spd")
                public String spd;
            }
        }

        public static class SuggestionBean {
            @SerializedName("comf")
            public ComfBean comf;
            @SerializedName("cw")
            public CwBean cw;
            @SerializedName("drsg")
            public DrsgBean drsg;
            @SerializedName("flu")
            public FluBean flu;
            @SerializedName("sport")
            public SportBean sport;
            @SerializedName("trav")
            public TravBean trav;
            @SerializedName("uv")
            public UvBean uv;

            public static class ComfBean {
                @SerializedName("brf")
                public String brf;
                @SerializedName("txt")
                public String txt;
            }

            public static class CwBean {
                @SerializedName("brf")
                public String brf;
                @SerializedName("txt")
                public String txt;
            }

            public static class DrsgBean {
                @SerializedName("brf")
                public String brf;
                @SerializedName("txt")
                public String txt;
            }

            public static class FluBean {
                @SerializedName("brf")
                public String brf;
                @SerializedName("txt")
                public String txt;
            }

            public static class SportBean {
                @SerializedName("brf")
                public String brf;
                @SerializedName("txt")
                public String txt;
            }

            public static class TravBean {
                @SerializedName("brf")
                public String brf;
                @SerializedName("txt")
                public String txt;
            }

            public static class UvBean {
                @SerializedName("brf")
                public String brf;
                @SerializedName("txt")
                public String txt;
            }
        }

        public static class DailyForecastBean {
            @SerializedName("astro")
            public AstroBean astro;
            @SerializedName("cond")
            public CondBean cond;
            @SerializedName("date")
            public String date;
            @SerializedName("hum")
            public String hum;
            @SerializedName("pcpn")
            public String pcpn;
            @SerializedName("pop")
            public String pop;
            @SerializedName("pres")
            public String pres;
            @SerializedName("tmp")
            public TmpBean tmp;
            @SerializedName("vis")
            public String vis;
            @SerializedName("wind")
            public WindBean wind;

            public static class AstroBean {
                @SerializedName("sr")
                public String sr;
                @SerializedName("ss")
                public String ss;
            }

            public static class CondBean {
                @SerializedName("code_d")
                public String codeD;
                @SerializedName("code_n")
                public String codeN;
                @SerializedName("txt_d")
                public String txtD;
                @SerializedName("txt_n")
                public String txtN;
            }

            public static class TmpBean {
                @SerializedName("max")
                public String max;
                @SerializedName("min")
                public String min;
            }

            public static class WindBean {
                @SerializedName("deg")
                public String deg;
                @SerializedName("dir")
                public String dir;
                @SerializedName("sc")
                public String sc;
                @SerializedName("spd")
                public String spd;
            }
        }

        public static class HourlyForecastBean {
            @SerializedName("date")
            public String date;
            @SerializedName("hum")
            public String hum;
            @SerializedName("pop")
            public String pop;
            @SerializedName("pres")
            public String pres;
            @SerializedName("tmp")
            public String tmp;
            @SerializedName("wind")
            public WindBean wind;

            public static class WindBean {
                @SerializedName("deg")
                public String deg;
                @SerializedName("dir")
                public String dir;
                @SerializedName("sc")
                public String sc;
                @SerializedName("spd")
                public String spd;
            }
        }
    }
}
