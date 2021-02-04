# A4_WS2020

-----------------------------------
Assignment 4: Software-Design / UML
-----------------------------------

## 4a Design: Simplified Media-Management-System (SMMS) ##

Create a software design for the following specification and document your design with a UML class diagram. 

Identify suitable classes, add attributes and operations, define inheritance and association relations as well as their multiplicities.
 
Note: 
- There is no programming work to be done!
- The specification is deliberately formulated in a vague way. 
- Where necessary, you should make meaningful assumptions and document them. 
- Create the diagram either manually or with a UML tool.
- Pay attention to the correct use of the UML notation.


### "Specification" ###

The SMMS manages various media types such as books, journals, CDs, and DVDs.
Each medium has a text description as well as various other properties.
Users of the SMMS can borrow the managed media.   

There are cases where there exist multiple copies of the same medium.
Each copy has a unique number. Furthermore, each copy has a status (borrowed, reserved, available, non-circulating).

The SMMS has predefined categories for medias. For books, these categories include, for example, "novel", "specialist book", "children's book".

The SMMS also manages its users. Each user has a unique user number.
Furthermore, besides the name also the day of birth, the address, phone number, and a changable password are stored.

Further requirements:

- In addition to individual media items, there are also grouped items. E.g., a book and a CD can only be borrowed together, so they are grouped. 
- There are different authorization-levels for users. A "superuser", for example, is able to borrow more and/or for longer periods compared to a "normal user". An administrator can add new media or change the category of a medium.

**Deliverables: One PDF-, PNG-, or JPG- file with a name starting with "4a_".**


## 4b Java-to-UML  ##

Use a UML-Tool (e.g., www.umlet.com) to draw a UML class diagram of the follow (senseless) Java code.

```java
import java.util.List;

abstract class C {
	protected static String name;
	private List<C> ds;

	public abstract int m();
}
```

```java
class C1 extends C implements I {
	private int counter;

	public C1(String s) {
		name = s;
	}

	public int m() {
		return 0;
	}
}
```

```java
class D {
	private int id;
	public C myC;
}
```

```java
public interface I {

}
```

```java
public interface J extends I {
	public int getID();
}
```


**Deliverables: One PDF-, PNG-, or JPG- file with a name starting with "4b_".**

## 4c UML-to-Java  ##

Translate the UML class diagram ```4c_UML.jpg``` to Java code.
Introduce dummy code if necessary, such that it compiles without errors.   

**Deliverables: A folder with a name starting with "4c_" containing the Java source files.**

