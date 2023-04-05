export interface Review{
    display_title: string
    mpaa_rating: string
    byline: string
    headline: string
    summary_short: string
    linkUrl: string
    multimedia_src: string
    count: number
};

export interface Comments{
    movieName: string
    name: string
    rating: number
    comment: string
}

export interface Search{
    search: string
}