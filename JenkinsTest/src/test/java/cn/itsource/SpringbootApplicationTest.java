package cn.itsource;

import redis.clients.jedis.Jedis;

class SpringbootApplicationTest{
    public static void main(String[] args) {
        Jedis jedis=new Jedis("127.0.0.1",6379);
        String response=jedis.ping();
        System.out.println(response);
        jedis.flushDB();
        System.out.println("name:"+jedis.get("name"));
        jedis.set("name","wangkui");
        System.out.println("name:"+jedis.get("name"));
    }


}