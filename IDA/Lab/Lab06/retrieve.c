/*******************************************************/
/*                                                     */
/*        C programme to retrieve documents            */
/*                                                     */
/*          Written for EE3J2 Data Mining              */
/*                                                     */
/*        Version 1: Martin Russell 28/01/04           */
/*                                                     */
/* Dept. Electronic, Electrical & Computer Engineering */
/*            University of Birmingham                 */
/*                                                     */
/*    To compile under linux:                          */
/*                 gcc -lm retrieve.c                  */
/*                 mv a.out retrieve                   */
/*                                                     */
/*    To run:                                          */
/*                 retrieve indexFile                  */
/*                                                     */
/*******************************************************/

#define MAX_STR_LEN 512

#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

/********************************************************/

/* structure to store document lengths */
struct docData {
  char *docName;
  float length;
  float sim;
};

/********************************************************/

/* structure to store document index item */
struct docIndex {
  char *docName;
  int count;
  float weight;
  struct docIndex *nextIndex;
};

/********************************************************/

/* struture to store linked list of words */
struct item {
  char *text;
  int wrdCount;
  int docCount;
  float IDF;
  struct docIndex *docList;
  struct item *nextItem;
};

/********************************************************/

/* Function to create new item in linked list */
void createNewIndex
(struct item *prevIt, struct item **newIt, struct item *nextIt, char *wrd, int wordCount, int docCount, float idf)
{
  int c;
  /* allocate memory for new linked list item */
  *newIt=calloc(1,sizeof(struct item));
  /* set pointer to new item */
  prevIt->nextItem=*newIt;
  /* set entries in new item */
  (*newIt)->nextItem=nextIt;
  (*newIt)->wrdCount=wordCount;
  (*newIt)->docCount=docCount;
  (*newIt)->IDF=idf;
  (*newIt)->text=calloc(strlen(wrd)+1,sizeof(char));
  for (c=0; c<strlen(wrd); c++)
    {
      (*newIt)->text[c]=wrd[c];
    }
  (*newIt)->text[c]='\0';
}

/********************************************************/

void createNewDocIndex(struct docIndex *docInd,struct docIndex **newIndex,char *documentName, int count, float weight)
{
  int c;
  struct docIndex *tmp;
  /* create space for new docIndex item in linked list */
  tmp=calloc(1,sizeof(struct docIndex));
  *newIndex=tmp;
  docInd->nextIndex=tmp;
  tmp->docName=calloc(strlen(documentName)+1,sizeof(char));
  for (c=0; c<strlen(documentName); c++) tmp->docName[c]=documentName[c];
  tmp->docName[c]='\0';
  /* set count */
  tmp->count=count;
  /* set weight */
  tmp->weight=weight;
  tmp->nextIndex=NULL;
}
/********************************************************/

struct docData *readIndex(FILE *index,struct item *root, struct item *end)
{
  int numTerms;
  int numDocs;
  int x;
  int term;
  int listSize;

  int num;
  int i;
  int d;
  char word[MAX_STR_LEN];
  char docName[MAX_STR_LEN];
  int wordCount;
  int docCount;
  float idf;
  float weight;

  struct item *curItem;
  struct item *tmpItem;
  struct docIndex *docInd;
  struct docIndex *newIndex;
  struct docData *docLen;

  /* First read document lengths */
  x=fscanf(index,"%d\n",&numDocs);
  if (x==EOF)
    {
      printf("ERROR (read numTerms): end of file reached\n");
      exit(1);
    }
  printf("number of documents=%d\n",numDocs);
  /* allocate space to store document lengths */
  docLen=calloc(numDocs,sizeof(struct docData));
  /* read document lengths */
  for (d=0; d<numDocs; d++)
    {
      int c;
      x=fscanf(index,"%s %f\n",word,&(docLen[d].length));
      if (x!=2)
	{
	  printf("ERROR reading document lengths\n");
	  exit(1);
	}
      /* allocate space for document name */
      docLen[d].docName=calloc(strlen(word)+1,sizeof(char));
      for (c=0; c<strlen(word); c++) docLen[d].docName[c]=word[c];
      docLen[d].docName[c]='\0';
      /* check */
      /* printf("%d, %s, %f\n",d+1,docLen[d].docName,docLen[d].length); */
      /* initialise score */
      docLen[d].sim=0;
    }

  /* Now read index */
  /* initialise */
  listSize=0;
  num=0;

  /* read number of terms in index */
  x=fscanf(index,"%d %d\n",&numTerms,&numDocs);
  if (x==EOF)
    {
      printf("ERROR (read numTerms): end of file reached\n");
      exit(1);
    }
  printf("\n");
  /* set root */
  root->wrdCount=numTerms;
  root->docCount=numDocs;

  for (term=0; term<numTerms; term++)
    {
      int d;
      /* read next 'term' line term */
      x=fscanf(index,"%d word=%s wordCount=%d docCount=%d IDF=%f\n",&i,word,&wordCount,&docCount,&idf);
      if (x!=5)
	{
	  printf("ERROR (read first term information): format error\n");
	  exit(1);
	}
      if (term==0)
	{
	  createNewIndex(root,&curItem,end,word,wordCount,docCount,idf);
	}
      else
	{
	  /* add new item to end of linked list */
	  createNewIndex(curItem,&tmpItem,end,word,wordCount,docCount,idf);
	  /* re-set pointer to current item */
	  curItem=tmpItem;
	}
      /* now create document list for this term */
      for (d=0; d<docCount; d++)
	{
	  /* read in next line from file */
	  x=fscanf(index,"   %d docName=%s count=%d weight=%f\n",&i,docName,&wordCount,&weight);
	  if (x!=4)
	    {
	      printf("ERROR (read document information): format error\n");
	      exit(1);
	    }
	  /* create structure to hold document index information */
	  if (d==0)
	    {
	      int c;
	      /* create first proper item in linked list */
	      docInd=calloc(1,sizeof(struct docIndex));
	      /* create pointer to first item */
	      curItem->docList=docInd;
	      /* fill curItem */
	      docInd->count=wordCount;
	      docInd->weight=weight;
	      docInd->docName=calloc(strlen(docName)+1,sizeof(char));
	      for (c=0; c<strlen(docName); c++) docInd->docName[c]=docName[c];
	      docInd->docName[c]='\0';
	      num++;
	    }
	  else
	    {
	      struct docInd *newDInd; 
	      /* attach doc index to docInd */
	      createNewDocIndex(docInd,&docInd,docName,wordCount,weight);
	      /* increment docIndex counter */
	      num++;
	    }
	}
    }
  return docLen;
}

/********************************************************/

/* Function to check linked list */
int checkList(struct item root)
{
  int i;
  int d;
  struct item *curItem;
  struct docIndex *curDoc;

  /* initialise */
  i=1;
  curItem=root.nextItem;
  curDoc=curItem->docList;
  printf("%d %d\n",root.wrdCount,root.docCount);
  while ((curItem->nextItem)!=NULL)
    {
      /* print word information */
      /* printf("%d %s wordCount=%d docCount=%d IDF=%f\n",i,curItem->text,curItem->wrdCount,curItem->docCount,curItem->IDF);*/
      /* now print document list */
      d=1;
      while (curDoc != NULL)
	{
	  /* printf("   %d docName=%s count=%d weight=%f\n",d,curDoc->docName, curDoc->count, curDoc->weight); */
	  curDoc=curDoc->nextIndex;
	  d++;
	}
      /* find pointer to next list item */
      curItem=curItem->nextItem;
      /* find pointer to document list for this item */
      curDoc=curItem->docList;
      i++;
    }
  return i;
}

/********************************************************/

int processQuery(FILE *queryFile,struct item root,struct docData *docLen, int numDocs)
{
  int freq;
  char word[MAX_STR_LEN];
  struct item *curItem;
  struct docIndex *curDoc;
  int numTerms;
  int t;
  float queryLen;
  int doc;
  int *rank;

  int argMax;
  float max;

  printf("\nquery analysis:\n===============\n\n");

  /* initialise */
  queryLen=0;
  freq=1;

  numTerms=root.wrdCount;

  /* while (fscanf(queryFile,"%s",word)!=EOF) */
  while (fscanf(queryFile,"%s %d\n",word,&freq)!=EOF)
  {
	
      /* find word in linked list */
      curItem=root.nextItem;
      t=0;
	printf(" %s %d\n",word,freq);
      while ((strcmp(word,curItem->text)!=0) && (t<numTerms))
	{
	  curItem=curItem->nextItem;
	  t++;
	}
      if (t==numTerms)
	{
	  printf("'%s' not in the index\n",word);
	}
      else
	{
	  int wrdFreq;
	  int docFreq;
	  float idf;
	  /* fund number of times word occurs in data */
	  wrdFreq=curItem->wrdCount;
	  /* find number of documents containing word */
	  docFreq=curItem->docCount;
	  /* find IDF */
	  idf=curItem->IDF;
	  printf("'%s' occurs %d times in %d documents (IDF=%f)\n",word,wrdFreq,docFreq,idf);
	  /* set pointer to start of document list for this word */
	  curDoc=curItem->docList;
	  /* increment query length */
	  queryLen+=(freq*curItem->IDF)*(freq*curItem->IDF);
	  while (curDoc != NULL)
	    {
	      char *docName;
	      int d;
	      /* increment similarities for this word */
	      docName=curDoc->docName;
	      /* look for this document in the document length array */
	      d=0;
	      while (strcmp(docName,docLen[d].docName)!=0) d++;
	      /* increment similarity */
	      /* docLen[d].sim+=(curDoc->weight)*(curItem->IDF)*freq*(curItem->IDF); */
	      docLen[d].sim+=(curDoc->weight)*freq*(curItem->IDF);
		/* print */
		printf("    documentName=%s, weight=%f, count=%d\n",docName,curDoc->weight,curDoc->count);
	      /* move on to next document in list */
	      curDoc=curDoc->nextIndex;
	    }
	}
    }
  queryLen=sqrt(queryLen);
  /* now normalise by document lengths */
  for (doc=0; doc<numDocs; doc++) docLen[doc].sim/=(docLen[doc].length * queryLen);

  /* find best document */
  argMax=0;
  max=0;
  printf("\nResults (documents with similarity > 0)\n======================================\n\n");
  for (doc=0; doc<numDocs; doc++)
    {
      if (docLen[doc].sim > 0.0) printf("document=%s sim=%f\n",docLen[doc].docName,docLen[doc].sim);
      if ((docLen[doc].sim) > max)
	{
	  max=docLen[doc].sim;
	  argMax=doc;
	}
    }
  printf("\n Best document is %s (%f)\n",docLen[argMax].docName,docLen[argMax].sim);
  return(argMax);
}

/********************************************************/

int main(int argc, char *argv[])
{
  struct item root;
  struct item end;
  struct item *curItem;
  struct item *prevItem;
  struct item *newItem;
  struct docIndex *docInd;
  struct docIndex *newIndex;
  struct docData *docLen;
  char nullText[1];
  int numDocs;

  FILE *indexFile;
  FILE *queryFile;

  /* set up end of linked list */
  end.wrdCount=0;
  end.docCount;
  nullText[0]='\0';
  end.text=calloc(1,sizeof(char));
  end.text=nullText;
  end.nextItem=NULL;
  end.docList=NULL;

  /* Check correct number of input parameters */
  if (argc!=3)
    {
      printf("format: retrieve indexFileName queryFileName\n");
      exit(1);
    }

  /* set pointer to index file */
  argv++;
  if ((indexFile=fopen(*argv,"r"))==NULL)
    {
      printf("Error: can't open index file %s\n",*argv);
      exit(1);
    }
  printf("index file open\n");

  /* set pointer to query file */
  argv++;
  if ((queryFile=fopen(*argv,"r"))==NULL)
    {
      printf("Error: can't open query file %s\n",*argv);
      exit(1);
    }
  printf("query file open\n");

  /* open index file */
  docLen=readIndex(indexFile,&root,&end);

  /* check index structure */
  checkList(root);

  /* read in 'zipfed' query file contents */
  /* assumes file contains list of words plus frequencies */
  processQuery(queryFile,root,docLen,root.docCount);
  
}
