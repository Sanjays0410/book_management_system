package com.cruds.demo;

public class Book 
{
	int BookISBN;
	String booktitle;
	String Category;
	int noofbooks;



	private Author author;

//	private Issue issue;

//	private Student student;





	public Book(int bookISBN) {
		super();
		BookISBN = bookISBN;
	}
	
	
	





/*	public Book(int bookISBN, Issue issue, Student student) {
		super();
		BookISBN = bookISBN;
		this.issue = issue;
		this.student = student;
	}

*/

	public Book(int bookISBN, String booktitle, String category, int noofbooks,Author auth) {
		super();
		BookISBN = bookISBN;
		this.booktitle = booktitle;
		Category = category;
		this.noofbooks = noofbooks;
		this.author=auth;

	}
/*	public Issue getIssue() {
		return issue;
	}
	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	*/
	public int getBookISBN() {
		return BookISBN;
	}
	public void setBookISBN(int bookISBN) {
		this.BookISBN = bookISBN;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public int getNoofbooks() {
		return noofbooks;
	}
	public void setNoofbooks(int noofbooks) {
		this.noofbooks = noofbooks;
	}


	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}


/*	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
*/

	@Override
	public String toString() {
		return "Book [Book_ISBN=" + BookISBN + ", book_title=" + booktitle + ", Category=" + Category + ", no_of_books="
				+ noofbooks + "]";
	}

}


