import {Analysis} from "../interfaces/Analysis";

export default class WebController{
    static async newAnalysis(content: string, name: string, date:string): Promise<Analysis> {
        const response = await fetch('http://localhost:8080/analysis', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ content, name, date }),
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
                    break;
                    case -2:
                        throw new Error("The file content is not valid!");
                        break;
                    default:
                        throw new Error("An unknown error occurred.");
                        break;
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