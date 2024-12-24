export interface User { 
    userId: number; 
    firstname: string; 
    lastname: string; 
    role: string; 
} 

export interface Record { 
    reimbId: number; 
    description: string; 
    amount: number; 
    status: string; 
    user: User; 
} // Include the nested user object