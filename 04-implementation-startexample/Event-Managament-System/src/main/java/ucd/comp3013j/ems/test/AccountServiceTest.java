package ucd.comp3013j.ems.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ucd.comp3013j.ems.model.dto.AccountDTO;
import ucd.comp3013j.ems.model.entities.*;
import ucd.comp3013j.ems.model.repos.*;
import ucd.comp3013j.ems.model.services.AccountService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private OrganiserRepository organiserRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private AccountService accountService;

    private AccountDTO accountDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // 创建一个 AccountDTO 对象用于测试
        accountDTO = new AccountDTO();
        accountDTO.setEmail("test@example.com");
        accountDTO.setName("John Doe");
        accountDTO.setPassword("password123");
        accountDTO.setRole("USER");

        // 设置密码编码器的行为
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword123");
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);  // 模拟 matches 方法
    }

    // 测试获取账户的方法
    @Test
    public void testGetAccountById_Found() {
        Customer customer = new Customer("test@example.com", "John Doe", "password123");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Account account = accountService.getAccount(1L);

        assertNotNull(account);
        assertEquals("test@example.com", account.getEmail());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAccountById_NotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        when(adminRepository.findById(1L)).thenReturn(Optional.empty());
        when(organiserRepository.findById(1L)).thenReturn(Optional.empty());

        Account account = accountService.getAccount(1L);

        assertNull(account);
        verify(customerRepository, times(1)).findById(1L);
        verify(adminRepository, times(1)).findById(1L);
        verify(organiserRepository, times(1)).findById(1L);
    }

    // 测试通过邮件获取账户
    @Test
    public void testGetAccountByEmail_Found() {
        Customer customer = new Customer("test@example.com", "John Doe", "password123");
        when(customerRepository.findByEmail("test@example.com")).thenReturn(customer);

        Account account = accountService.getAccount("test@example.com");

        assertNotNull(account);
        assertEquals("test@example.com", account.getEmail());
        verify(customerRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    public void testGetAccountByEmail_NotFound() {
        when(customerRepository.findByEmail("test@example.com")).thenReturn(null);
        when(adminRepository.findByEmail("test@example.com")).thenReturn(null);
        when(organiserRepository.findByEmail("test@example.com")).thenReturn(null);

        Account account = accountService.getAccount("test@example.com");

        assertNull(account);
        verify(customerRepository, times(1)).findByEmail("test@example.com");
        verify(adminRepository, times(1)).findByEmail("test@example.com");
        verify(organiserRepository, times(1)).findByEmail("test@example.com");
    }

    // 测试创建账户
    @Test
    public void testCreateAccount_Success() {
        when(customerRepository.findByEmail(accountDTO.getEmail())).thenReturn(null);

        accountService.createAccount(accountDTO);

        // 验证 customerRepository.save 被调用
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testCreateAccount_EmailAlreadyExists() {
        when(customerRepository.findByEmail(accountDTO.getEmail())).thenReturn(new Customer());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            accountService.createAccount(accountDTO);
        });

        assertEquals("Email already exists", exception.getMessage());
    }

    // 测试更新账户
    @Test
    public void testUpdateAccount_Success() {
        Customer customer = new Customer("test@example.com", "John Doe", "password123");
        when(customerRepository.findByEmail("test@example.com")).thenReturn(customer);

        accountDTO.setName("Updated Name");
        accountService.updateAccount(accountDTO);

        assertEquals("Updated Name", customer.getName());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testUpdateAccount_NotFound() {
        when(customerRepository.findByEmail("test@example.com")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            accountService.updateAccount(accountDTO);
        });

        assertEquals("Account not found", exception.getMessage());
    }

    // 测试删除Customer
    @Test
    public void testDeleteCustomer() {
        // 创建一个 Customer 对象
        Customer customer = new Customer("test@example.com", "John Doe", "password123");

        // 模拟 customerRepository 和 ticketRepository
        when(customerRepository.findByEmail("test@example.com")).thenReturn(customer);

        List<Ticket> tickets = new ArrayList<>();
        Ticket ticket = new Ticket();  // 创建一个 Ticket 对象
        tickets.add(ticket);
        when(ticketRepository.findByCustomer(customer)).thenReturn(tickets);

        // 调用删除方法
        accountService.deleteCustomer(customer);

        // 验证 ticketRepository 的 findByCustomer 被调用
        verify(ticketRepository, times(1)).findByCustomer(customer);

        // 验证 ticketRepository 的 delete 被调用
        verify(ticketRepository, times(1)).delete(ticket);

        // 验证 customerRepository 的 deleteById 被调用
        verify(customerRepository, times(1)).deleteById(customer.getId());
    }

    // 测试删除Organiser
    @Test
    public void testDeleteOrganiser() {
        // 创建一个 Organiser 对象
        Organiser organiser = new Organiser("test@example.com", "John Doe", "password123", "Company", "Address", "12345");

        // 模拟 organiserRepository 和 eventRepository
        when(organiserRepository.findByEmail("test@example.com")).thenReturn(organiser);
        List<Event> events = new ArrayList<>();
        Event event = new Event();  // 创建一个 Event 对象
        events.add(event);
        when(eventRepository.findByOrganiserId(1L)).thenReturn(events);

        // 调用删除方法
        accountService.deleteOrganiser(1L);

        // 验证 eventRepository 的 findByOrganiserId 被调用
        verify(eventRepository, times(1)).findByOrganiserId(1L);

        // 验证 organiserRepository 的 deleteById 被调用
        verify(organiserRepository, times(1)).deleteById(1L);

        // 验证 event 的 setOrganiser 被调用
        verify(eventRepository, times(1)).save(event);
    }

    // 测试修改密码
    @Test
    public void testChangePassword_Success() {
        Customer customer = new Customer("test@example.com", "John Doe", "encodedPassword123");
        when(customerRepository.findByEmail("test@example.com")).thenReturn(customer);

        // 调用 changePassword 方法
        accountService.changePassword("test@example.com", "password123", "newPassword");

        // 验证密码编码器的 encode 方法是否被调用
        verify(passwordEncoder, times(1)).encode("newPassword");

        // 验证 customerRepository 的 save 方法是否被调用
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testChangePassword_IncorrectCurrentPassword() {
        Customer customer = new Customer("test@example.com", "John Doe", "encodedPassword123");
        when(customerRepository.findByEmail("test@example.com")).thenReturn(customer);

        // 模拟密码匹配失败
        when(passwordEncoder.matches("password123", "encodedPassword123")).thenReturn(false);

        // 调用 changePassword 方法，应该抛出 RuntimeException
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            accountService.changePassword("test@example.com", "password123", "newPassword");
        });

        assertEquals("Current password is incorrect", exception.getMessage());
    }
}
