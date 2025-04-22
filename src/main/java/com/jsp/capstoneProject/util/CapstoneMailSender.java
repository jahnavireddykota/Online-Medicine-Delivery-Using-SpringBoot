package com.jsp.capstoneProject.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.jsp.capstoneProject.DAO.MemberDAO;
import com.jsp.capstoneProject.entity.Drug;
import com.jsp.capstoneProject.entity.Member;
import com.jsp.capstoneProject.entity.Payment;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class CapstoneMailSender 
{
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	MemberDAO memberDAO;
	
	public void requestEnable(Member member) throws MessagingException
	{
		MimeMessage message=javaMailSender.createMimeMessage();
		message.setFrom("jahnavikota03@gmail.com");
		message.setSubject("Member Verification");
		message.setRecipients(MimeMessage.RecipientType.CC, "jahnavikota04@gmail.com");
		String htmlContent=" <img style='border: 5px solid skyblue; border-radius: 20px; box-shadow: 5px 5px 5px gray;' width='800px' height='400px' src='https://www.allaboutchemistry.net/storage/2021/09/image-71.png'>\n"
				+ " <h4>Hi Admin</h4>\nNew member is <b>registered</b> to our application\nPlease verify it"
				+ "<center><table border=1 width='400px' heigth='300px'><tr><th>Member Id</th><td>"+member.getId()+"</td></tr>"
						+ "<tr><th>Member Name</th><td>"+member.getName()+"</td></tr>"
								+ "<tr><th>Member Email id :</th><td>"+member.getEmail()+"</td></tr>"
										+ "<tr><th>Member Gender</th><td>"+member.getGender()+"</td></tr>"
												+ "<tr><th>Member Mobile no: </th><td>"+member.getMoblie()+"</td></tr>"
								
						+ "</table></center>"+"<h4>Thank you <b>Team-8</b></h4>";
				;
		message.setContent(htmlContent, "text/html; charset=utf-8");		
		javaMailSender.send(message);		
	}

	public void sendOrderDetails(Payment payment) throws MessagingException 
	{
		   // Create MimeMessage
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Retrieve member ID and find the corresponding member
        int memberId = payment.getMemberid();
        Member findMemberById = memberDAO.fetchById(memberId); // Assuming memberDao is defined and injected

        // Set the recipient email address
        helper.setTo(findMemberById.getEmail());
        helper.setSubject("Order Details");

        // HTML content with table format
        StringBuilder content = new StringBuilder();
        content.append("<html>")
               .append("<body>")
               .append("<h3>Your order has been placed successfully</h3>")
               .append("<table>")
               .append("<tr>")
               .append("<th>Drug Name</th>")
               .append("<th>Drug Price</th>")
               .append("</tr>");

        // Iterate over the drugs in the payment and add them to the table
        for (Drug drug : payment.getDrug()) 
        {
            content.append("<tr>")
                   .append("<td>").append(drug.getName()).append("</td>")
                   .append("<td>").append(drug.getPrice()).append("</td>")
                   .append("</tr>");
        }

        content.append("</table>")
               .append("</body>")
               .append("</html>");

        // Set the email content as HTML
        helper.setText(content.toString(), true);

        // Send the email
        javaMailSender.send(message);
    
	}

               

	
}
