package ucd.comp3013j.ems.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ucd.comp3013j.ems.model.entities.Account;
import ucd.comp3013j.ems.model.entities.Administrator;
import ucd.comp3013j.ems.model.entities.Customer;
import ucd.comp3013j.ems.model.entities.Organiser;
import ucd.comp3013j.ems.model.enums.Role;
import ucd.comp3013j.ems.model.repos.AdminRepository;
import ucd.comp3013j.ems.model.repos.CustomerRepository;
import ucd.comp3013j.ems.model.repos.OrganiserRepository;
import ucd.comp3013j.ems.model.services.AccountDetailsService;
import ucd.comp3013j.ems.websecurity.AccountWrapper;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountDetailsServiceTest {

    // 模拟 AdminRepository 仓库
    @Mock
    private AdminRepository adminRepository;

    // 模拟 CustomerRepository 仓库
    @Mock
    private CustomerRepository customerRepository;

    // 模拟 OrganiserRepository 仓库
    @Mock
    private OrganiserRepository organiserRepository;

    // 被测试的 AccountDetailsService 服务
    @InjectMocks
    private AccountDetailsService accountDetailsService;

    // 模拟 Administrator 对象
    @Mock
    private Administrator mockAdministrator;

    // 在每个测试方法执行之前运行的初始化方法
    @BeforeEach
    public void setUp() {
        // 打开 Mockito 的 mock 功能
        MockitoAnnotations.openMocks(this);

        // 设置 mockAdministrator 的行为
        when(mockAdministrator.getEmail()).thenReturn("test@example.com");
        when(mockAdministrator.getPassword()).thenReturn("password");
    }

    // 测试用户在 AdminRepository 中存在的情况
    @Test
    public void testLoadUserByUsername_FoundInAdminRepository() {
        // 创建一个 Administrator 对象，模拟数据库中找到的用户
        Administrator admin = new Administrator("test@example.com", "password", "ADMIN");

        // 模拟 adminRepository 返回该管理员对象
        when(adminRepository.findByEmail("test@example.com")).thenReturn(admin);

        // 调用 accountDetailsService 的方法
        AccountWrapper accountWrapper = (AccountWrapper) accountDetailsService.loadUserByUsername("test@example.com");

        // 断言返回的 accountWrapper 不为空，并验证用户名是否正确
        assertNotNull(accountWrapper);
        assertEquals("test@example.com", accountWrapper.getUsername());

        // 验证 adminRepository 的 findByEmail 方法是否被调用了一次
        verify(adminRepository, times(1)).findByEmail("test@example.com");
    }

    // 测试用户在 CustomerRepository 中存在的情况
    @Test
    public void testLoadUserByUsername_FoundInCustomerRepository() {
        // 创建一个真实的 Customer 对象
        Customer customer = new Customer("test@example.com", "password", "USER");

        // 模拟 adminRepository 返回 null，表示用户不在 AdminRepository 中
        // 模拟 customerRepository 返回该 Customer 对象
        when(adminRepository.findByEmail("test@example.com")).thenReturn(null);
        when(customerRepository.findByEmail("test@example.com")).thenReturn(customer);

        // 调用 accountDetailsService 的方法
        AccountWrapper accountWrapper = (AccountWrapper) accountDetailsService.loadUserByUsername("test@example.com");

        // 断言返回的 accountWrapper 不为空，并验证用户名是否正确
        assertNotNull(accountWrapper);
        assertEquals("test@example.com", accountWrapper.getUsername());

        // 验证 customerRepository 的 findByEmail 方法是否被调用了一次
        verify(customerRepository, times(1)).findByEmail("test@example.com");
    }

    // 测试用户在 OrganiserRepository 中存在的情况
    @Test
    public void testLoadUserByUsername_FoundInOrganiserRepository() {
        // 创建一个真实的 Organiser 对象
        Organiser organiser = new Organiser("test@example.com", "John Doe", "password",
                "Some Company", "1234 Elm St.", "123-456-7890");

        // 模拟 adminRepository 和 customerRepository 返回 null，
        // 模拟 organiserRepository 返回该 Organiser 对象
        when(adminRepository.findByEmail("test@example.com")).thenReturn(null);
        when(customerRepository.findByEmail("test@example.com")).thenReturn(null);
        when(organiserRepository.findByEmail("test@example.com")).thenReturn(organiser);

        // 调用 accountDetailsService 的方法
        AccountWrapper accountWrapper = (AccountWrapper) accountDetailsService.loadUserByUsername("test@example.com");

        // 断言返回的 accountWrapper 不为空，并验证用户名是否正确
        assertNotNull(accountWrapper);
        assertEquals("test@example.com", accountWrapper.getUsername());

        // 验证 organiserRepository 的 findByEmail 方法是否被调用了一次
        verify(organiserRepository, times(1)).findByEmail("test@example.com");
    }

    // 测试用户不存在的情况
    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // 模拟三个仓库都返回 null，表示没有找到该用户
        when(adminRepository.findByEmail("test@example.com")).thenReturn(null);
        when(customerRepository.findByEmail("test@example.com")).thenReturn(null);
        when(organiserRepository.findByEmail("test@example.com")).thenReturn(null);

        // 验证调用 loadUserByUsername 方法时，应该抛出 UsernameNotFoundException 异常
        assertThrows(UsernameNotFoundException.class, () -> {
            accountDetailsService.loadUserByUsername("test@example.com");
        });
    }
}

