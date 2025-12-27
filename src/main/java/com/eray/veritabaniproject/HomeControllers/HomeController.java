package com.eray.veritabaniproject.HomeControllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.eray.veritabaniproject.Services.BagisService;
import com.eray.veritabaniproject.Models.Bagis;


@Controller
public class HomeController {
    
    @Autowired
    private BagisService service;

    @GetMapping("/")
    public String Home() {
        return "index";
    }

   @RequestMapping("/bagis")
   public String bagis(Model model) {
    List<Bagis> bagisList = service.listAll();
    model.addAttribute("bagisList", bagisList);
    return "bagis";
   }
    @RequestMapping("/yeniBagis")
   public String yeniBagis(Model model) {
    Bagis bagis = new Bagis();
    model.addAttribute("Bagis", bagis); 
    return "yeniBagis";
   }

   @RequestMapping(value = "/savebagis", method = RequestMethod.POST)
   public String saveBagis(@ModelAttribute("Bagis") Bagis bagis, RedirectAttributes eklendiPopup) {
       service.save(bagis);
       eklendiPopup.addFlashAttribute("successMessage", "Yeni bağış noktası başarıyla eklendi.");
       return "redirect:/bagis";
   }


    @RequestMapping("/editBagis/{id}")
   public ModelAndView editBagis(@PathVariable(name="id") int id) {
    ModelAndView mav = new ModelAndView("editBagis");
    Bagis bagis = service.get(id);
    mav.addObject("Bagis", bagis);
    return mav;
   }

   @RequestMapping(value = "/updatebagis", method = RequestMethod.POST)
   public String updateBagis(@ModelAttribute("Bagis") Bagis bagis, RedirectAttributes updatePopup) {
       service.save(bagis);
       updatePopup.addFlashAttribute("editMessage", "Bağış noktası başarıyla güncellendi.");
       return "redirect:/bagis";
   }

    @RequestMapping("/silBagis/{id}")
   public String silBagis(@PathVariable(name="id") int id, RedirectAttributes silindiPopup)
    {
    
    service.delete(id);
    silindiPopup.addFlashAttribute("silMessage","Silme işlemi başarılı");
    return "redirect:/bagis";
   }
   
}

