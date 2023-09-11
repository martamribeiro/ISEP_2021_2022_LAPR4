# 3001 - "As Sales Manager, I want to create a new questionnaire to be further answered by customers meeting the specified criteria (e.g.: have ordered a given product; belong to a given age group)."



# 1. Requisitos


**UC3001:** Como Sales Manager, pretendo criar um novo questionário para ser respondido por clientes, que se encontram em determinada situação (ex: ter encomendado determinado produto, pertencer a um dado grupo de idade)

A interpretação feita deste requisito foi no sentido de permitir ao sales manager criar um questionário, caso este seja válido.


# 2. Análise

## 2.1. Respostas do Cliente

>Q1: As I understand, the questionnaire will have to be created through the command-line interface of our application and then exported in order for it's grammar to be validated. Is my interpretation correct? Can we export it to XML, for example, and validate it's grammar afterwards?
>
>A1: There is no need to import/export data. Both components should be integrated. I advice you to talk with technical experts (faculty of Lab classes of course units).

>Q2: What are the constraints to the survey alphanumeric code and the description?
>
>A2: Code: alphanumeric value with 15 chars max;
> 
> Description: non-empty short sentence (e.g.: 40 chars).

>Q3: Can you specify / define what business rules are associated to Questionnaire, Section and Question? (Eg: Questionnaire ID only has 9 characters / follows an expression).
> 
>A3: Basic business rules related with Questionnaire, Section and Question are already available on the specifications document, namely on Table 1, 2 and 3. Teams must adopt common-sense when applying other criteria such as min/max chars length and support/explain the rationale taken.

>Q4: When a Section has a Question tagged as "Mandatory" should the section become "Mandatory" as well?
> 
> Can you specify what you mean when a Question/Section is tagged with "condition dependent" and the type of conditions to be set.
> 
>A4: If the "obligatoriness" of a question/section is "condition dependent", it means it is "mandatory" when the associated condition evaluates as "true" and it is "optional" otherwise.
> 
> The question/answer is not straightforward. However, some consistency/coherence must exist/be assured between the "obligatoriness" of the section and of the question. You must also assure consistency with the "repeatability" information of the section.
> 
> Please, check carefully the example provided on section 5.1.3 of the specifications document where you can find "mandatory" sections (e.g.: section 1), "optional" sections (e.g.: section 8) and "condition dependent" sections (e.g.: section 3, 4 and 5). You can also find  "repeatable" sections (e.g.: section 6).
> 
> A "mandatory" or "condition dependant" section does not imply any "obligatoriness" on the questions.
> 
> An "optional" section implies that all questions are also "optional".
> 
> If a question is "mandatory", it means the user needs to answer such question no matter what is stated at the section it appears on.
> 
> If a question is "optional", it means that is up to the user to answer or not the question no matter what is stated at the section it appears on.
> 
> If a question is "condition dependent", it means the system needs to evaluate the associated condition to determine how to proceed, i.e. as "mandatory" or as "optional" question.

>Q5: In the document, we have "Repeatability - Optional condition stating if the questions of this section are to be answered more than once. At least two kinds of conditions need to be supported: (i) based on numeric answers or (ii) on a set of selected values."
> 
> Could you better specify what you mean by "repeatability"? Maybe give an example of how you wanted it to work in a user perspective. (Do you have to answer each question X amount of times / the section repeats itself X times).
> 
> Is this "repeatability" attribute just repeating the same question to the customer?  Or does something change?
> 
>A5: Please, check the example presented on section 5.1.3 of the specifications' document. In particular, consider the last sentence regarding section 6.

>Q6: The same section can be present in more than one questionnaire?
> 
> The same question can be present in more than one section?
> 
>A6: Yes, that can happen.
> 
> However, there is no intend to reutilize questions and/or sections. If that happens, the user will type the question/section again.

>Q7: When creating a questionnaire should the user give a file with the questionnaire or write each question and section trough the command line?
> 
>A7: First of all, and to be clear, notice that creating a surveys has two distinct set of data:
> 
> alphanumeric code, a description, the period (in days) it will be performed and a set of rules that allows the system to determine the survey target audience; and
> 
> the intended questionnaire, i.e. the title, the welcome message, sections and questions.
> 
> I believe your question concerns the second set of data, right?
> 
> If so, your two options are acceptable. If the file is used, what is really important is the file content and not the file itself.
> 
> Moreover, notice that no matter what option you take, at the end, all the input information must be validated, which includes parsing the data regarding the "intended questionnaire".

>Q8: Regarding the creation/validation of a new questionnaire/surveys, what is the expected result after its creation?
>
> For example just store the file loaded with the grammar and generate a html page (suggested by the LPROG PL teacher) as output, or should the application store it in the database for a possible answer in the application?
> 
>A8: I believe you and your team would find the answers you are looking for if you carefully read and interpret the following available information:
> 
> section 3.3 of the specifications' document;
> 
> US 3000 and 3001 of Sprint C as well as US 3002 and 3501 of Sprint D (including acceptance criteria).
> 
> Within this scope, at no point is being requested an HTML UI.
> 
> It is also clear that the survey is, first, created then distributed for customers to answer it, answers are further collected and, finally, some statistics will be computed.
> 
> Regarding "... store the file loaded", I recommend you to read previous posts about this topic.
> 
> There is no need to upload file. However, if a file is upload then you should notice that what is really important is not the file but its content.

>Q9: In the requested grammar, is it needed to distinguish the different types of questions? For example, if the user says a certain question is multiple choice, shall we make the user input the possible options specifically or do we let the user write the content of the question as he prefers?
> 
>A9: Well, the question type is need and useful for some reason. The way you/your team take (technical) advantage of it is up to you. I really advice you to debate this issue with technical advisers (i.e. lab classes teachers).

>Q10: When creating a new Survey can we recycle an already existing questionnaire? And should only the answers be saved, or should the Survey/Questionnaire also be kept?
> 
>A10: (to be answered)


## 2.2. Regras de Negócio

* "The set of questions/answers composing the questionnaire should be expressed and validated (parsed) using the grammar being developed for this purpose."
* The rules of the questionnaires are specified in the Client's answers.



# 3. Integração/Demonstração

Esta User Story depende da User Story 3000, uma vez que é necessária a existência da gramática, para validar os questionários

# 4. Observações
