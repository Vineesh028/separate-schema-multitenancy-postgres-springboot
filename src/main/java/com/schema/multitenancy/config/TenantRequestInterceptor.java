package com.schema.multitenancy.config;

import java.util.Optional;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.schema.multitenancy.util.Constant;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TenantRequestInterceptor implements AsyncHandlerInterceptor {

	@Value("${hibernate.connection.url}")
	private String postgresUrl;

	@Value("${hibernate.connection.username}")
	private String postgresUsername;

	@Value("${hibernate.connection.password}")
	private String postgresPassword;

	@Value("${migration.location}")
	private String migrationLocation;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		return Optional.ofNullable(request).map(req -> getTenantIdFromHeader(req))
				.map(tenant -> setTenantContext(tenant)).orElse(false);
	}

	private String getTenantIdFromHeader(HttpServletRequest req) {
		return req.getHeader(Constant.TENANT_ID);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		TenantContext.clear();
	}

	private boolean setTenantContext(String tenant) {
		Flyway flyway = Flyway.configure().dataSource(postgresUrl, postgresUsername, postgresPassword).schemas(tenant)
				.defaultSchema(tenant).locations(migrationLocation).baselineOnMigrate(true).load();

		// MigrationInfoService migrationInfoService = flyway.info();
		// MigrationInfo[] migrationInfos = migrationInfoService.pending();

		flyway.migrate();

		TenantContext.setCurrentTenant(tenant);
		return true;
	}
}
