package com.sin.http

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.*

def Get(url){
	hc = HttpClients.createDefault()
	get = new HttpGet(url)
	res = hc.execute(get)
	InputStream is = res.getEntity().getContent()
	BufferedReader br = new BufferedReader(new InputStreamReader(is))
	sb = new StringBuffer()
	l = br.readLine()
	while(l!=null){
		sb.append(l)
		l = br.readLine()
	}
	return sb.toString()
}

//println Get("https://api.douban.com/v2/user/~me")