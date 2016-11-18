package fi.haagahelia.course.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.course.domain.CategoryRepository;
import fi.haagahelia.course.domain.Product;
import fi.haagahelia.course.domain.ProductRepository;



@Controller
public class ProductController {
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	private List<String> shoppingList=new ArrayList<String>();
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	
	public String greeting(Model model)
	{
		return "hello";
	}
	
	// Show all products
    @RequestMapping(value="/login")
    public String login() {	
    	httpSession.setAttribute("products", shoppingList);
        return "login";
        
    }	
	
	@RequestMapping(value="/productlist", method=RequestMethod.GET)
    public String productList(Model model) {	
        model.addAttribute("products", repository.findAll());
        return "productlist";
    }
	
	// RESTful service to get all products
    @RequestMapping(value="/products", method = RequestMethod.GET)
    public @ResponseBody List<Product> productListRest() {	
        return (List<Product>) repository.findAll();
    }   
    
 // RESTful service to get book by id
    @RequestMapping(value="/products/{id}", method = RequestMethod.GET)
    public @ResponseBody Product findProductRest(@PathVariable("id") Long productId) {	
    	return repository.findOne(productId);
    }
    
    @RequestMapping(value = "/add")
    public String addProduct(Model model){
    	model.addAttribute("product", new Product());
    	model.addAttribute("categories", crepository.findAll());
        return "addproduct";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Product product){
        repository.save(product);
        return "redirect:productlist";
    }    
    
    @RequestMapping(value = "/buy/{id}", method = RequestMethod.GET)
    public String buyProduct(@PathVariable("id") Long productId, Model model) {
    	
    	//here should be method to save the products in the session then sends them to a new template with a list of products in a shopping cart
    	//it will show the name attribute of the product object then calculate the total price
    	
    	List<Product> prods = (List<Product>)httpSession.getAttribute("products");

    	Product newProd=repository.findOne(productId);
        prods.add(newProd);

        httpSession.setAttribute("products",prods);
    	
        model.addAttribute("products", prods);
        
        double price;
        double sum=0;
        
        for (int i=0; i<prods.size();i++)
        {
        	Product listitem =prods.get(i);
        	sum+=listitem.getPrice();
        }
        
        model.addAttribute("totalprice", sum);
    	
        return "shoppingcart";
    }     
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("id") Long productId, Model model) {
    	repository.delete(productId);
        return "redirect:../productlist";
    }     
}
