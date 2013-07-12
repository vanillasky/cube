package kr.co.datastreams.cube.collector.http;

/**
 * Created with IntelliJ IDEA.
 * User: shkim
 * Date: 13. 7. 8
 * Time: 오후 12:37
 * To change this template use File | Settings | File Templates.
 */
public interface HttpStatusCode {

    int OK = 200;
    int FOUND = 302;
    int NOT_MODIFIED = 304; // This means that there is no need to retransmit the resource, since the client still has a previously-downloaded copy.
    int BAD_REQUEST = 400; // The request cannot be fulfilled due to bad syntax.
    int UNAUTHORIZED = 401; // authentication is required and has failed or has not yet been provided
    int FORBIDDEN = 403;    // The request was a valid request, but the server is refusing to respond to it.
    int NOT_FOUND = 404;    // The requested resource could not be found but may be available again in the future.
    int NOT_ACCEPTABLE = 406;  // The requested resource is only capable of generating content not acceptable according to the Accept headers sent in the request.

}
