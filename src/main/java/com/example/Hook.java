package com.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.http.HttpMethod;

@Entity
@SequenceGenerator(name="AGGREGATE_SEQUENCE", sequenceName="AGGREGATE_SEQUENCE", allocationSize=1)
@Table(name="Hook")
class Hook {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AGGREGATE_SEQUENCE")
	@Column(name = "ID")
	private Long id;

	@SuppressWarnings("unused")
	private Hook() {
	}

	public Hook(HttpMethod method, String uri, String cron) {
		this.method = method.name();
		this.uri = uri;
		this.cron = cron;
	}

	@Column(name = "CRON")
	private String cron;

	@Column(name = "URI")
	private String uri;

	@Column(name = "METHOD")
	private String method = HttpMethod.POST.name();

	@Column(name = "VERSION")
	private long version = 0L;

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public String toString() {
		return method + " [id=" + id + ", uri=" + uri + "]";
	}

}