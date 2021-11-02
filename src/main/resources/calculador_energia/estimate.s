.section .data

.section .bss

.section .text				# section identifier (text)

	.global estimate		#declare the function int estimate(d) as global, to be used in C
     

################# DECLARATION OF THE FUNCTION 	################################

estimate:				# function estimate start
					# we'll be using a formula for the estimation derived from: 
					# Charging time of battery (in seconds) = 3600*((capacidade*(cargarestante/100)/eficiencia)*100)/(correntedebitada)
					# using correntedebitada if it is under 10% of capacidade as the recommended value to charging current percentage,
					# or capacidade*10/100, if not, observing that correntedebitada<capacidade*10% <=>10*correntedebitada<capacidade  
					# which would be equivalent to state 
					# Charging time of battery (in seconds) = 36000*(capacidade*cargarestante)/(10*correntedebitada*eficiencia)
					# or Charging time of battery (in seconds) = 36000*(capacidade*cargarestante)/(capacidade*eficiencia)
					# which is the one we'll be using
							
################# PROLOGUE ################################################################################

	pushl %ebp			# save previous stack frame pointer
	movl %esp, %ebp 		# the stack frame pointer for sum function
	subl $12, %esp			# reserving 3 spaces of local variables to store intermediary values
	
	
############## SAVE REGISTRIES ############################################################################ 

	pushl %ebx			#stores current value of %ebx in stack
	pushl %edi			#stores current value of %edi in stack
	#pushl %esi			#stores current value of %esi in stack
		
################ CALLER ####################################################################################
	
 					# caller is responsible for :
 					# %eax , %edx and %ecx
 					# save only those that are used	
 
############################################################################################################ 

#body

################## To store the values of the inserted parameters ####################################	
	movl $0,%eax			#initializes the return value
	movl 8(%ebp), %ebx		#stores the value of *d, pointer for the Struct dados	
		
################# Begining the cycle to determine the estimate time value and store the information #########################
	
	movl (%ebx), %eax		#to store the capacidade value in %eax and in a local variable, as it might be used twice, in different uses
	movl %eax, -4(%ebp)
	
	addl $4, %ebx			# moves pointer to eficiencia
	movl (%ebx), %ecx		# stores eficiencia in %ecx and into a local variable
	movl %ecx, -8(%ebp)
	imull %ecx			# multiplies capacidade by eficiencia, which might be our divisor in the final calculation 		
	movl %eax, -12(%ebp)		# stores potencial final divisor into a local variable
	
	movl -4(%ebp), %eax		#  we begin to move the value stored in -4(%ebp) to %eax again to obtain 36000*capacidade
	movl $36000, %ecx
	imull %ecx			# 36000*capacidade is now stored in %eax
	
	addl $4, %ebx
	movl (%ebx), %ecx		# to obtain  36000*(capacidade*cargarestante), we now move cargarestante, stored in the third position in the structure, into %ecx
	imull %ecx			# 36000*(capacidade*cargarestante) is now in %eax
	
	addl $4, %ebx			# moves pointer to correntedebitada position
	movl (%ebx), %ecx		# we now put correntedebitada into %ecx, to begin the comparison between the 10%*capacidade and correntedebitada, so we can choose which formula to use
					
					# using shifts to multiply %ecx by 10 without changing %eax
	sall $1, %ecx			# multiplies %ecx by 2
	movl %ecx, %edi	 	# stores into %edi the double of %ecx				
	sall $2, %ecx			# multiplies the current value of %ecx (the double of the original) by four, obtaining eight times the original
	addl %edi, %ecx 		# adds the double (in %edi) with the last product, obtaining 10*%ecx , that is, 10*correntedebitada
	
	cmpl %ecx, -4(%ebp)		# we will now compare 10*correntedebitada with capacidade 
	je usa_capacidade
	jl usa_capacidade
	
	movl -8(%ebp), %edi		# to obtain 10*correntedebitada*eficiencia, which will be the divisor, we begin to move evidencia (in local variable) into %edi
	imul %edi, %ecx		# and then multiply it by 10*correntedebitada, that is in %ecx
	
	cdq
	idivl %ecx			# the quocient is now stored into %eax, with the remaining in %edx (we only need the quocient - the remaining will be afecting only the tenths or
					# hundreths of the seconds units value which is of little consequence, when converted to minutes or hours
	jmp end
	
	
usa_capacidade:

	cdq 							
	movl -12(%ebp), %ecx		# to obtain  36000*(capacidade*cargarestante/(capacidade*eficiencia), we move (capacidade*eficiencia), stored in -12(%ebp), into de divisor %ecx
	idivl %ecx			# the quocient is now stored into %eax, with the remaining in %edx (we only need the quocient - the remaining will be afecting only the tenths or
					# hundreths of the seconds units value which is of little consequence, when converted to minutes or hours
	

end:
	
########################## RESTORE REGISTRIES #####################################

	#popl %esi			
	popl %edi
	popl %ebx
	
########################## EPILOGUE ############################################

	movl %ebp, %esp		# restore the previous stack pointer ("clear" the stack)	
	popl %ebp			# restore the previous stack frame pointer
	
######################### RESTORE ###########################################	
	ret
