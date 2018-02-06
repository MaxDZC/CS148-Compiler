/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.Http;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Max
 */
public class Request {
    
	public static String getMethod() {
		return null; // POST|GET|REQUEST|...
	}

	public static String get(String key) {
		Map<String, String> queries = parseQueryString(key);

		return key;
	}

	//

	protected static Map<String, String> parseQueryString(String qs) {
	    Map<String, String> result = new HashMap<>();

	    if (qs == null)
	        return result;

	    int last = 0, next, l = qs.length();
	    while (last < l) {
	        next = qs.indexOf('&', last);
	        if (next == -1)
	            next = l;

	        if (next > last) {
	            int eqPos = qs.indexOf('=', last);
	            try {
	                if (eqPos < 0 || eqPos > next)
	                    result.put(URLDecoder.decode(qs.substring(last, next), "utf-8"), "");
	                else
	                    result.put(URLDecoder.decode(qs.substring(last, eqPos), "utf-8"), URLDecoder.decode(qs.substring(eqPos + 1, next), "utf-8"));
	            } catch (UnsupportedEncodingException e) {
	                throw new RuntimeException(e);
	            }
	        }
	        last = next + 1;
	    }
	    return result;
	}
}
