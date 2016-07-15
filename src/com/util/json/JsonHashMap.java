package com.util.json;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JsonHashMap extends HashMap{
	
	@Override
	public Object put(Object key, Object value) {
		// TODO Auto-generated method stub
		Object put = null;
		if(value instanceof Map){
			put = super.put(key, value);
		}else{
			put = super.put(key, "\"" + (value.toString().replace("\"", "\\\"")) + "\"");
		}
		return put;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String jsonStr = "";
		String valuePair[] = super.toString().split(",");
		for(int i=0;i<valuePair.length;i++){
			jsonStr = jsonStr + valuePair[i].replaceFirst("=", ":");
		}
		return "("+jsonStr.replace("\r","\\r").replace("\n","\\n")+")";
	}
}
