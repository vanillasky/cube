package kr.co.datastreams.cube.collector.test.http;

import kr.co.datastreams.cube.collector.http.HttpAgentResponse;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 8
 * Time: 오후 3:07
 * To change this template use File | Settings | File Templates.
 */
public class HttpAgentResponseTest {

    String simpleJsonFile = "cube-crawler/src/test/java/kr/co/datastreams/cube/crawler/test/http/simple.json";
    String text = "{\"gender\" : \"MALE\", \"verified\" : false}";

    @Test
    public void testHttpClient_asString() throws Exception {
        FileInputStream fis = new FileInputStream(new File(simpleJsonFile));
        HttpAgentResponse res = new HttpAgentResponse(fis, null, 200);
        String result = res.asString();
        assertEquals(text, result);
    }

//    @Test
//    public void testHttpClient_asJson() throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonFactory factory = mapper.getJsonFactory();
//        JsonParser parser = factory.createJsonParser(fis);
//        //JsonNode obj = mapper.readTree(parser);
//        Simple s = parser.readValueAs(Simple.class);
//        System.out.println(s);
//    }
//
//    public static class Simple {
//        public static class Name {
//            public String getFirst() {
//                return first;
//            }
//
//            public void setFirst(String first) {
//                this.first = first;
//            }
//
//            public String getLast() {
//                return last;
//            }
//
//            public void setLast(String last) {
//                this.last = last;
//            }
//
//            String first;
//            String last;
//
//
//        }
//
//        private Name name;
//        private String gender;
//        private boolean verified;
//        private String userImage;
//
//        public Name getName() {
//            return name;
//        }
//
//        public void setName(Name name) {
//            this.name = name;
//        }
//
//        public String getGedger() {
//            return gender;
//        }
//
//        public void setGender(String genger) {
//            this.gender = genger;
//        }
//
//        public boolean isVerified() {
//            return verified;
//        }
//
//        public void setVerified(boolean verified) {
//            this.verified = verified;
//        }
//
//        public String getUserImage() {
//            return userImage;
//        }
//
//        public void setUserImage(String userImage) {
//            this.userImage = userImage;
//        }
//    }
}
