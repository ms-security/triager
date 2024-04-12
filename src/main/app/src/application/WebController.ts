import {Analysis} from "../interfaces/Analysis";

export default class WebController{
    static async newAnalysis(file: File, name: string, date: string): Promise<Analysis> {
        const formData = new FormData();
        formData.append("file", file);
        formData.append("name", name);
        formData.append("date", date);
        const response = await fetch('http://localhost:8080/analysis', {
            method: 'POST',
            body: formData
        });
        if (response.ok) {
            const analysis: Analysis = await response.json();
            console.log('File sent successfully');
            console.log("Analysis received:", analysis);
            return analysis;
        } else {
            // Handle different errors based on the response
            const errorData = await response.json();
            switch (errorData.id) {
                case -1:
                    throw new Error("The file is empty!");
                    case -2:
                        throw new Error("The file content is not valid!");
                    default:
                        throw new Error("An unknown error occurred.");
                }
        }
    }

    static async fetchAllAnalyses(): Promise<Analysis[]> {
        const response = await fetch('http://localhost:8080/analysis', {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
            }
        });
        if (!response.ok) {
            throw new Error('Failed to fetch analyses from the server');
        }
        // Parse the JSON response and return it
        const analyses: Analysis[] = await response.json();
        console.log('Analyses fetched successfully:', analyses);
        return analyses;
    }

    static async deleteAnalysis(analysisId: number) {
        const response = await fetch(`http://localhost:8080/analysis/${analysisId}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            // Handle failure
            throw new Error('Failed to delete analysis');
        }
    }

    static async toggleFavoriteStatus(analysisId: number): Promise<void> {
        const response = await fetch(`http://localhost:8080/analysis/${analysisId}/favorite`, {
            method: 'PUT'
        });
        console.log('Favorite status updated');
        if (!response.ok) {
            throw new Error('Failed to update favorite status');
        }
    }
}