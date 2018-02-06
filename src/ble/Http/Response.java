/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.Http;

/**
 *
 * @author Max
 */
public class Response {
	protected String body;
	protected Integer status;

	public void setBody(String body) {
		this.body = body;
	}

	public String getBody() {
		return this.body;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setHeader() {

	}

	public void setHeaders() {

	}

	public String getHeaders() {
            return "";
        }

	public String getHeader(String name) {
            return "";
        }
}
