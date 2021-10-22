/*
 * package com.loginservice.securingmicroservices.controller;
 * 
 * 
 * 
 * import static org.junit.Assert.*;
 * 
 * 
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * 
 * 
 * import javax.servlet.http.HttpServletRequest;
 * 
 * 
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.mockito.InjectMocks; import org.mockito.Mock; import org.mockito.Mockito;
 * import org.springframework.http.ResponseEntity; import
 * org.springframework.web.client.RestTemplate; import
 * com.loginservice.securingmicroservices.model.User;
 * 
 * 
 * 
 * public class AdminUpdateControllerTest extends AbstractTestClass {
 * 
 * @Mock private RestTemplate restTemplate;
 * 
 * @Mock private HttpServletRequest hsr;
 * 
 * 
 * @InjectMocks private AdminUpdateController auc;
 * 
 * @Before public void setUp() { super.setUp(); }
 * 
 * 
 * 
 * 
 * @Test public void testGetAllUser() { String url =
 * "http://localhost:8020/api/getAll"; List<User> l = new ArrayList<User>();
 * ResponseEntity<List> list = ResponseEntity.ok(l);
 * Mockito.when(restTemplate.getForEntity(url, List.class)).thenReturn(list);
 * System.out.println(list); System.out.println(auc.getAllUser(hsr));
 * assertEquals(auc.getAllUser(hsr).getBody(),l); }
 * 
 * 
 * 
 * @Test public void testGetAllActiveUsers() { String url =
 * "http://localhost:8020/api/getAllActiveUsers"; List<User> l = new
 * ArrayList<User>(); ResponseEntity<List> list = ResponseEntity.ok(l);
 * Mockito.when(restTemplate.getForEntity(url, List.class)).thenReturn(list);
 * assertEquals(auc.getAllActiveUsers(hsr),list); }
 * 
 * 
 * 
 * }
 */