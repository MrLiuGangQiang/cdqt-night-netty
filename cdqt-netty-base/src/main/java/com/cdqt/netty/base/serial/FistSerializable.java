package com.cdqt.netty.base.serial;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * FistSerializable
 *
 * @author LiuGangQiang Create in 2020/06/06
 */
public class FistSerializable {

	/**
	 * fist protostuff schema cache use {@link ConcurrentHashMap}
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	private static Map<Class<?>, Schema<?>> cacheSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();

	/**
	 * protostuff objenesis plase see {@link Objenesis}
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	private static Objenesis objenesis = new ObjenesisStd(true);
	/**
	 * singleton protostuff serializable
	 * 
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	private static FistSerializable instance;

	/**
	 * get singleton protostuff serializable
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 */
	public static FistSerializable getInstance() {
		if (instance == null) {
			instance = new FistSerializable();
		}
		return instance;
	}

	/**
	 * get target schema if no schema are available, create and return
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 * @param clazz class
	 * @return {@link T}
	 */
	private <T> Schema<T> getSchema(Class<T> clazz) {
		@SuppressWarnings("unchecked")
		Schema<T> schema = (Schema<T>) cacheSchema.get(clazz);
		if (schema == null) {
			schema = RuntimeSchema.getSchema(clazz);
			if (schema != null) {
				cacheSchema.put(clazz, schema);
			}
		}
		return schema;
	}

	/**
	 * protostuff serialize
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 * @param obj entity
	 * @return {@link byte[]}
	 */
	public <T> byte[] serialize(T obj) {
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) obj.getClass();
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		try {
			Schema<T> schema = getSchema(clazz);
			return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		} finally {
			buffer.clear();
		}
	}

	/**
	 * protostuff deserialize you need to instantiate objects yourself
	 *
	 * @author LiuGangQiang Create in 2020/06/06
	 * @param buffer bytes
	 * @param clazz  class
	 * @return {@link T}
	 */
	public <T> T deserialize(byte[] buffer, Class<T> clazz) {
		try {
			T obj = objenesis.newInstance(clazz);
			Schema<T> schema = getSchema(clazz);
			ProtostuffIOUtil.mergeFrom(buffer, obj, schema);
			return obj;
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}
}