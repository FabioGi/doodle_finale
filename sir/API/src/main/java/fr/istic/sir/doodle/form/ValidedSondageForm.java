package fr.istic.sir.doodle.form;

import java.util.List;

public class ValidedSondageForm {
		private long id;
		private List<String>emails;
		private String content;
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		private String subject;
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public List<String> getEmails() {
			return emails;
		}
		public void setEmails(List<String> emails) {
			this.emails = emails;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
}
