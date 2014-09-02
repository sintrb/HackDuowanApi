package com.sin.http

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.AbstractHttpEntity
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.InputStreamEntity
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClients


def StreamToString(is){
	BufferedReader br = new BufferedReader(new InputStreamReader(is))
	sb = new StringBuffer()
	l = br.readLine()
	while(l!=null){
		sb.append(l)
		l = br.readLine()
	}
	is.close()
	return sb.toString()
}

def Execute(req){
	hc = HttpClients.createDefault()
	res = hc.execute(req)
	return res.getEntity().getContent()
}

def Get(url){
	get = new HttpGet(url)
	return Execute(get)
}

class JCEEntity extends AbstractHttpEntity{

	@Override
	public InputStream getContent() throws IOException, IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getContentLength() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public boolean isRepeatable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStreaming() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void writeTo(OutputStream os) throws IOException {
		os.write(1);
		os.write(2);
	}
}

def Post(url, data){
	post = new HttpPost(url)
	if (data.class == String)
		post.setEntity(new StringEntity("hello world"))
	else if (java.io.InputStream.isInstance(data))
		post.setEntity(new InputStreamEntity(data))
	else
		println "unknown data type: "+data.class
	return Execute(post)
}




data = new ByteArrayInputStream("abcdfasdfasdfasdflkjaskldfjaksldfklasdfdjfkalsdfklasjdfklasjdfklabcdfasdfasdfasdflkjaskldfjaksldfklasdfdjfkalsdfklasjdfklasjdfklabcdfasdfasdfasdflkjaskldfjaksldfklasdfdjfkalsdfklasjdfklasjdfklabcdfasdfasdfasdflkjaskldfjaksldfklasdfdjfkalsdfklasjdfklasjdfklabcdfasdfasdfasdflkjaskldfjaksldfklasdfdjfkalsdfklasjdfklasjdfklabcdfasdfasdfasdflkjaskldfjaksldfklasdfdjfkalsdfklasjdfklasjdfklabcdfasdfasdfasdflkjaskldfjaksldfklasdfdjfkalsdfklasjdfklasjdfklabcdfasdfasdfasdflkjaskldfjaksldfklasdfdjfkalsdfklasjdfklasjdfkl".bytes)
println "abcd".bytes
println data
println Post("http://172.16.0.170:1122", data)



