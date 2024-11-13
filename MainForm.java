import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Random;

public class MainForm extends JFrame {
    private JTextArea inputArea;
    private JTextArea outputArea;
    private JButton generateButton;
    private JButton bubbleSortButton;
    private JButton selectionSortButton;
    private JButton clearButton;
    private SortingAlgorithm sortingAlgorithm;
    
    // Custom colors with good contrast
    private final Color PRIMARY_BLUE = new Color(25, 91, 255);
    private final Color SECONDARY_BLUE = new Color(52, 109, 255);
    private final Color BACKGROUND_COLOR = new Color(240, 242, 245);
    private final Color TEXT_COLOR = new Color(30, 41, 59);
    
    public MainForm() {
        sortingAlgorithm = new SortingAlgorithm();
        initializeFrame();
        initializeComponents();
    }
    
    private void initializeFrame() {
        setTitle("Aplikasi Sorting Algorithms");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND_COLOR);
        setLayout(new BorderLayout(15, 15));
    }
    
    private void initializeComponents() {
        // Add header
        add(createHeaderPanel(), BorderLayout.NORTH);
        
        // Add main content
        add(createMainPanel(), BorderLayout.CENTER);
        
        // Add footer
        add(createFooterPanel(), BorderLayout.SOUTH);
        
        // Add padding
        ((JPanel)getContentPane()).setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(PRIMARY_BLUE);
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("Visualisasi Algoritma Sorting");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        
        return headerPanel;
    }
    
    private JPanel createMainPanel() {
        // Initialize components
        inputArea = createStyledTextArea();
        outputArea = createStyledTextArea();
        
        generateButton = createStyledButton("Generate Angka Random", PRIMARY_BLUE);
        bubbleSortButton = createStyledButton("Bubble Sort", SECONDARY_BLUE);
        selectionSortButton = createStyledButton("Selection Sort", SECONDARY_BLUE);
        clearButton = createStyledButton("Clear", new Color(239, 68, 68));
        
        // Add action listeners
        generateButton.addActionListener(e -> generateRandomNumbers());
        bubbleSortButton.addActionListener(e -> bubbleSort());
        selectionSortButton.addActionListener(e -> selectionSort());
        clearButton.addActionListener(e -> clearAll());
        
        // Create panels
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        
        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBackground(BACKGROUND_COLOR);
        inputPanel.add(new JScrollPane(inputArea), BorderLayout.CENTER);
        inputPanel.setBorder(createTitledBorder("Data Input"));
        
        // Output panel
        JPanel outputPanel = new JPanel(new BorderLayout(5, 5));
        outputPanel.setBackground(BACKGROUND_COLOR);
        outputPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        outputPanel.setBorder(createTitledBorder("Hasil Sorting"));
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.add(generateButton);
        buttonPanel.add(bubbleSortButton);
        buttonPanel.add(selectionSortButton);
        buttonPanel.add(clearButton);
        
        // Add to main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(outputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        return mainPanel;
    }
    
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(BACKGROUND_COLOR);
        
        JLabel footerLabel = new JLabel("© 2024 Sorting Visualization");
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footerLabel.setForeground(new Color(71, 85, 105));
        footerPanel.add(footerLabel);
        
        return footerPanel;
    }
    
    private JTextArea createStyledTextArea() {
        JTextArea textArea = new JTextArea(8, 40);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        textArea.setForeground(TEXT_COLOR);
        textArea.setBackground(Color.WHITE);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return textArea;
    }
    
    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(backgroundColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });
        
        return button;
    }
    
    private TitledBorder createTitledBorder(String title) {
        TitledBorder border = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            title
        );
        border.setTitleFont(new Font("Segoe UI", Font.BOLD, 14));
        border.setTitleColor(TEXT_COLOR);
        return border;
    }
    
    private void generateRandomNumbers() {
        Random rand = new Random();
        int[] numbers = new int[10];
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rand.nextInt(100);
            sb.append(numbers[i]);
            if (i < numbers.length - 1) {
                sb.append(", ");
            }
        }
        
        sortingAlgorithm.setArray(numbers);
        inputArea.setText(sb.toString());
        outputArea.setText("");
    }
    
    private void bubbleSort() {
        String result = sortingAlgorithm.bubbleSort();
        if (result.equals("Array kosong")) {
            showError("Harap generate angka terlebih dahulu!");
        } else {
            outputArea.setText(result);
        }
    }
    
    private void selectionSort() {
        String result = sortingAlgorithm.selectionSort();
        if (result.equals("Array kosong")) {
            showError("Harap generate angka terlebih dahulu!");
        } else {
            outputArea.setText(result);
        }
    }
    
    private void clearAll() {
        inputArea.setText("");
        outputArea.setText("");
        sortingAlgorithm.setArray(new int[0]);
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(
            this,
            message,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}