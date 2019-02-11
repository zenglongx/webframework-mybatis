package com.xx.webframework.restapi;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xx.webframework.domain.User;
import org.apache.shiro.subject.Subject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestApiApplicationTests extends AbstractShiroTest{

	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documentationConfiguration(this.restDocumentation))
				.build();

		//1.  Create a mock authenticated Subject instance for the test to run:
		Subject subjectUnderTest = mock(Subject.class);
		when(subjectUnderTest.isAuthenticated()).thenReturn(true);
		User user = new User();
		user.setUserId(1);
		user.setUsername("admin");
		user.setPassword("admin");
		user.setRealName("测试帐号");
		user.setRoleId(1);
		when(subjectUnderTest.getPrincipal()).thenReturn(user);

		//2. Bind the subject to the current thread:
		setSubject(subjectUnderTest);
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRoot() throws Exception {
		this.mockMvc.perform(get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("index"));

		this.mockMvc.perform(get("/product/list").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("product-list"));


		//------------------------//
		/**     user test         */
		//------------------------//

		this.mockMvc.perform(post("/login")
				.param("username","test")
				.param("password","test")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("login" ,
						requestParameters(
							parameterWithName("username").description("The user's username"),
							parameterWithName("password").description("The user's password")
						),
						relaxedResponseFields(
							fieldWithPath("code").description("0:success;1:error")
						)
				));

		this.mockMvc.perform(get("/user/list")
				.param("searchKey","")
				.param("pageNum","1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("user-list",
						requestParameters(
							parameterWithName("searchKey").optional().description(""),
							parameterWithName("pageNum").optional().description("default 1")
						),
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error"),
								subsectionWithPath("data.list").description("用户列表 <<resources-user-detail, User>>")
						)
				));

		this.mockMvc.perform(get("/user/all")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("user-list-all",
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error"),
								subsectionWithPath("data").description("用户列表 <<resources-user-detail, User>>")
						)
				));
		this.mockMvc.perform(get("/user/queryByRoleId")
				.param("roleId","1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("user-query-by-roleid",
						requestParameters(
								parameterWithName("roleId").optional().description("角色标识")
						),
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error"),
								subsectionWithPath("data").description("用户列表 <<resources-user-detail, User>>")
						)
				));
		this.mockMvc.perform(post("/user/updateUserRole")
				.content("{\"roleId\":1,\"userIds\":[1]}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("user-set-role",
						requestFields(
								fieldWithPath("roleId").description("角色标识"),
								fieldWithPath("userIds").description("用户标识数组")
						),
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error")
						)
				));
		this.mockMvc.perform(post("/user/save")
				.content("{\"username\":\"test\",\"realName\":\"test\"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("user-save",
						requestFields(
								fieldWithPath("username").description("帐号"),
								fieldWithPath("realName").description("真实姓名")
						),
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error")
						)
				));
		this.mockMvc.perform(get("/user/detail/3")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("user-detail",
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error"),
								fieldWithPath("data.userId").description("用户标识"),
								fieldWithPath("data.username").description("帐号"),
								fieldWithPath("data.realName").description("真实姓名")
						)
				));
		this.mockMvc.perform(delete("/user/delete/3")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("user-delete",
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error")
						)
				));

		//------------------------//
		/**     role test         */
		//------------------------//

		this.mockMvc.perform(get("/role/list")
				.param("searchKey","")
				.param("pageNum","1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("role-list",
						requestParameters(
								parameterWithName("searchKey").optional().description(""),
								parameterWithName("pageNum").optional().description("default 1")
						),
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error"),
								subsectionWithPath("data.list").description("角色列表 <<resources-role-detail, Role>>")
						)
				));

		this.mockMvc.perform(get("/role/all")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("role-list-all",
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error"),
								subsectionWithPath("data").description("角色列表 <<resources-role-detail, Role>>")
						)
				));
		this.mockMvc.perform(post("/role/save")
				.content("{\"name\":\"test\",\"code\":\"test\"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("role-save",
						requestFields(
								fieldWithPath("name").description("角色名称"),
								fieldWithPath("code").description("角色编码")
						),
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error")
						)
				));
		this.mockMvc.perform(get("/role/detail/3")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("role-detail",
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error"),
								fieldWithPath("data.roleId").description("角色标识"),
								fieldWithPath("data.name").description("角色名称"),
								fieldWithPath("data.code").description("角色编码")
						)
				));
		this.mockMvc.perform(delete("/role/delete/3")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("role-delete",
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error")
						)
				));
		//------------------------//
		/**     permission test         */
		//------------------------//

		this.mockMvc.perform(get("/permission/list")
				.param("searchKey","")
				.param("pageNum","1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("permission-list",
						requestParameters(
								parameterWithName("searchKey").optional().description(""),
								parameterWithName("pageNum").optional().description("default 1")
						),
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error"),
								subsectionWithPath("data.list").description("权限列表 <<resources-permission-detail, Permission>>")
						)
				));

		this.mockMvc.perform(get("/permission/all")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("permission-list-all",
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error"),
								subsectionWithPath("data").description("权限列表 <<resources-permission-detail, Permission>>")
						)
				));
		String responseJson = this.mockMvc.perform(post("/permission/save")
				.content("{\"name\":\"test\",\"code\":\"test\"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("permission-save",
						requestFields(
								fieldWithPath("name").description("权限名称"),
								fieldWithPath("code").description("权限编码")
						),
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error")
						)
				)).andReturn().getResponse().getContentAsString();
		int permissionId = this.objectMapper.readTree(responseJson).get("data").asInt();
		this.mockMvc.perform(get("/permission/detail/"+permissionId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("permission-detail",
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error"),
								fieldWithPath("data.permissionId").description("权限标识"),
								fieldWithPath("data.name").description("权限名称"),
								fieldWithPath("data.code").description("权限编码")
						)
				));
		this.mockMvc.perform(delete("/permission/delete/"+permissionId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("permission-delete",
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error")
						)
				));
		//------------------------//
		/**     menu test         */
		//------------------------//

		this.mockMvc.perform(get("/menu/tree")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("menu-tree",
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error"),
								subsectionWithPath("data").description("菜单树 <<resources-menu-detail, Menu>>")
						)
				));

		String responseJson2 = this.mockMvc.perform(post("/menu/save")
				.content("{\"name\":\"test\",\"parentId\":\"1\",\"permissionId\":\"1\"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("menu-save",
						requestFields(
								fieldWithPath("name").description("菜单名称"),
								fieldWithPath("permissionId").description("权限标识"),
								fieldWithPath("parentId").description("父亲标识")
						),
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error")
						)
				)).andReturn().getResponse().getContentAsString();
		int menuId = this.objectMapper.readTree(responseJson2).get("data").asInt();
		this.mockMvc.perform(get("/menu/detail/"+menuId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("menu-detail",
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error"),
								fieldWithPath("data.menuId").description("菜单标识"),
								fieldWithPath("data.name").description("菜单名称"),
								fieldWithPath("data.parentId").description("父亲标识")
						)
				));
		this.mockMvc.perform(delete("/menu/delete/"+menuId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("menu-delete",
						relaxedResponseFields(
								fieldWithPath("code").description("0:success;1:error")
						)
				));
	}
	@After
	public void tearDownSubject() {
		//3. Unbind the subject from the current thread:
		clearSubject();
	}

}
