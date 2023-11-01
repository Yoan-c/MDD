import { Comment } from "./comment.interface"

export interface PostItem {
    id?: string
    title?: string
    post?: string
    idTopic?: string
    idUser?: string
    idComment?: Comment[]
    created_at?: Date
    updated_at?: Date
}